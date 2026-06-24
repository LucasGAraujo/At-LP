package PetFriends_Almoxarifado.DTO;

import PetFriends_Almoxarifado.domain.Quantidade;

import java.util.List;

public record PedidoProntoParaSeparacaoDTO(
        String pedidoId,
        String prioridadeFrete,
        List<ItemSeparacaoDTO> itens
) {
    public record ItemSeparacaoDTO(
            String codigoDeBarras,
            Quantidade quantidade
    ) {}
}