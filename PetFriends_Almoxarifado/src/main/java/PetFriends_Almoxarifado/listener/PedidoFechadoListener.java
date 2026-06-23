package PetFriends_Almoxarifado.listener;

import PetFriends_Almoxarifado.DTO.PedidoProntoParaSeparacaoDTO;
import PetFriends_Almoxarifado.config.RabbitMQConfig;
import PetFriends_Almoxarifado.service.AlmoxarifadoService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoFechadoListener {

    private final AlmoxarifadoService almoxarifadoService;

    public PedidoFechadoListener(AlmoxarifadoService almoxarifadoService) {
        this.almoxarifadoService = almoxarifadoService;
    }

    @RabbitListener(queues = RabbitMQConfig.FILA_ALMOXARIFADO_PEDIDO_FECHADO)
    public void receberEventoPedidoFechado(PedidoProntoParaSeparacaoDTO payload) {
        try {
            almoxarifadoService.iniciarSeparacao(payload);

        } catch (Exception e) {
            System.err.println("Erro ao processar o pedido " + payload.pedidoId() + ": " + e.getMessage());
            throw e;
        }
    }
}