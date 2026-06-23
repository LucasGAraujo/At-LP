package PetFriends_Almoxarifado.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class CodigoDeBarras {
    private String numero;
    public CodigoDeBarras(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("O código de barras não pode ser vazio.");
        }
        if (!numero.matches("\\d{13}")) {
            throw new IllegalArgumentException("Código de barras inválido. Deve conter exatamente 13 números.");
        }
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodigoDeBarras that = (CodigoDeBarras) o;
        return Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}