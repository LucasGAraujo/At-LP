package PetFriends_Pedidos.domain;

import PetFriends_Pedidos.domain.ENUM.StatusPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.NOVO;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private String nomeCliente;
    private String documentoCliente;

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(String nomeCliente, String documentoCliente, String logradouro, String bairro, String cep, String cidade, String estado) {
        this.nomeCliente = nomeCliente;
        this.documentoCliente = documentoCliente;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
        item.setPedido(this);
    }
}