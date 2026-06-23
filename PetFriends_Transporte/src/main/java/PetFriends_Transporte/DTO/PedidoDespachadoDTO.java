package PetFriends_Transporte.DTO;

public record PedidoDespachadoDTO(
        String pedidoId,
        String dataLimiteEntrega,
        Integer volumes,
        DestinatarioDTO destinatario,
        EnderecoDTO enderecoEntrega
) {
    public record DestinatarioDTO(
            String nome,
            String documentoRecebedor
    ) {}

    public record EnderecoDTO(
            String logradouro,
            String bairro,
            String cep,
            String cidade,
            String estado
    ) {}
}