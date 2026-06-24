package PetFriends_Almoxarifado.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class Quantidade {

    @Column(name = "quantidade")
    @JsonValue
    private Integer valor;

    public Quantidade(Integer valor) {
        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public Quantidade adicionar(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade adicionada deve ser maior que zero.");
        }
        return new Quantidade(this.valor + quantidade);
    }

    public Quantidade remover(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade removida deve ser maior que zero.");
        }

        if (this.valor < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }

        return new Quantidade(this.valor - quantidade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantidade that)) return false;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}