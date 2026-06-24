package PetFriends_Pedidos.DTO;

public record PedidoDespachadoDTO(
        Long pedidoId,
        Integer volumes,
        String dataLimiteEntrega,
        DestinatarioDTO destinatario,
        EnderecoDTO enderecoEntrega
)  {
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