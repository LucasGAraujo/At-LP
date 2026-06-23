package PetFriends_Almoxarifado.domain;
import PetFriends_Almoxarifado.domain.ENUM.StatusPreparacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_ordem_preparacao")
@Getter
@NoArgsConstructor
public class OrdemDePreparacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId; // Referência ao ID do serviço de Pedidos

    @Enumerated(EnumType.STRING)
    private StatusPreparacao status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ordem_id")
    private List<ItemPreparacao> itens = new ArrayList<>();

    public OrdemDePreparacao(Long pedidoId) {
        this.pedidoId = pedidoId;
        this.status = StatusPreparacao.PENDENTE;
    }

    public void adicionarItem(CodigoDeBarras codigoDeBarras, int quantidade) {
        this.itens.add(new ItemPreparacao(codigoDeBarras, quantidade));
    }
}