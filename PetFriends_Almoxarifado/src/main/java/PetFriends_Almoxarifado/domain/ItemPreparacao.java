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
    private int quantidade;

    public ItemPreparacao(CodigoDeBarras codigoDeBarras, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.codigoDeBarras = codigoDeBarras;
        this.quantidade = quantidade;
    }
}