package PetFriends_Pedidos.listener;

import PetFriends_Pedidos.DTO.PedidoDespachadoDTO;
import PetFriends_Pedidos.DTO.PedidoProntoParaSeparacaoDTO;
import PetFriends_Pedidos.config.RabbitMQConfig;
import PetFriends_Pedidos.domain.Pedido;
import PetFriends_Pedidos.repository.PedidoRepository;
import PetFriends_Pedidos.publisher.PedidoEventPublisher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class AlmoxarifadoSeparadoListener {

    private final PedidoEventPublisher pedidoEventPublisher;
    private final PedidoRepository pedidoRepository;

    public AlmoxarifadoSeparadoListener(PedidoEventPublisher pedidoEventPublisher, PedidoRepository pedidoRepository) {
        this.pedidoEventPublisher = pedidoEventPublisher;
        this.pedidoRepository = pedidoRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.FILA_PEDIDOS_ALMOXARIFADO_SEPARADO)
    public void receberAvisoQueFoiSeparado(PedidoProntoParaSeparacaoDTO payload) {
        System.out.println(" Vendas: Recebi o aviso do Almoxarifado. O pedido " + payload.pedidoId() + " está na doca!");

        try {
            Pedido pedido = pedidoRepository.findById(Long.valueOf(payload.pedidoId()))
                    .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + payload.pedidoId()));

            PedidoDespachadoDTO.DestinatarioDTO dest = new PedidoDespachadoDTO.DestinatarioDTO(
                    pedido.getNomeCliente(),
                    "12345678900"
            );

            PedidoDespachadoDTO.EnderecoDTO end = new PedidoDespachadoDTO.EnderecoDTO(
                    pedido.getLogradouro(),
                    pedido.getBairro(),
                    pedido.getCep(),
                    "Rio",
                    "RJ"
            );

            PedidoDespachadoDTO despacho = new PedidoDespachadoDTO(
                    pedido.getId(),
                    1,
                    "3",
                    dest,
                    end
            );
            pedidoEventPublisher.publicarPedidoDespachado(despacho);
            System.out.println(" Vendas: Pedido do " + pedido.getNomeCliente() + " enviado para o Transporte com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao processar despacho: " + e.getMessage());
            throw e;
        }
    }
}