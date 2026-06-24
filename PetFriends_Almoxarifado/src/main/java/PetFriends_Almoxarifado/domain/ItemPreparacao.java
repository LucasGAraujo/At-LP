package PetFriends_Almoxarifado.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_item_preparacao")
@Getter
@NoArgsConstructor
public class ItemPreparacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CodigoDeBarras codigoDeBarras;

    @Embedded
    private Quantidade quantidade;

    public ItemPreparacao(CodigoDeBarras codigoDeBarras, Quantidade quantidade) {
        this.codigoDeBarras = codigoDeBarras;
        this.quantidade = quantidade;
    }
}