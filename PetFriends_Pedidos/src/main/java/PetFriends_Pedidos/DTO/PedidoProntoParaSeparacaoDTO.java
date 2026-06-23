package PetFriends_Pedidos.DTO;

import java.util.List;

public record PedidoProntoParaSeparacaoDTO(
        String pedidoId,
        String prioridadeFrete,
        List<ItemSeparacaoDTO> itens
) {
    public record ItemSeparacaoDTO(
            String codigoDeBarras,
            Integer quantidade
    ) {}
}