package PetFriends_Pedidos.service;


import PetFriends_Pedidos.DTO.PedidoDespachadoDTO;
import PetFriends_Pedidos.DTO.PedidoProntoParaSeparacaoDTO;
import PetFriends_Pedidos.domain.ENUM.StatusPedido;
import PetFriends_Pedidos.domain.Pedido;
import PetFriends_Pedidos.publisher.PedidoEventPublisher;

import PetFriends_Pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoOrquestradorService {

    private final PedidoRepository repository;
    private final PedidoEventPublisher publisher;

    public PedidoOrquestradorService(PedidoRepository repository, PedidoEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Transactional
    public void processarPagamentoConfirmado(Long pedidoId) {
        Pedido pedido = repository.findById(pedidoId).orElseThrow();
        pedido.setStatus(StatusPedido.FECHADO);
        repository.save(pedido);

        var itensDTO = pedido.getItens().stream()
                .map(item -> new PedidoProntoParaSeparacaoDTO.ItemSeparacaoDTO(
                        item.getCodigoDeBarras(),
                            new PedidoProntoParaSeparacaoDTO.QuantidadeDTO(item.getQuantidade())))
                .toList();

        var payload = new PedidoProntoParaSeparacaoDTO(
                pedido.getId().toString(),
                "NORMAL",
                itensDTO
        );

        publisher.publicarPedidoFechado(payload);
    }

    @Transactional
    public void despacharPedido(Long pedidoId) {
        Pedido pedido = repository.findById(pedidoId).orElseThrow();

        pedido.setStatus(StatusPedido.EM_TRANSITO);
        repository.save(pedido);

        var payload = new PedidoDespachadoDTO(
                pedido.getId(),
                2,
                "3",
                new PedidoDespachadoDTO.DestinatarioDTO("João", "21999999999"),
                new PedidoDespachadoDTO.EnderecoDTO("Rua A", "Bairro B", "20000-000", "Rio", "RJ")
        );

        publisher.publicarPedidoDespachado(payload);
    }
}