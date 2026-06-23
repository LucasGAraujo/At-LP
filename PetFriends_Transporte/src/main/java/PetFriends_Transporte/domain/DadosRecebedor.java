package PetFriends_Transporte.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class DadosRecebedor {

    private String nomeRecebedor;
    private String documentoRecebedor;

    public DadosRecebedor(String nomeRecebedor, String documentoRecebedor) {
        if (nomeRecebedor == null || nomeRecebedor.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome de quem recebeu o pacote é obrigatório.");
        }
        if (documentoRecebedor == null || documentoRecebedor.trim().isEmpty()) {
            throw new IllegalArgumentException("O documento de quem recebeu o pacote é obrigatório.");
        }
        this.nomeRecebedor = nomeRecebedor.toUpperCase();
        this.documentoRecebedor = documentoRecebedor.replaceAll("[^0-9a-zA-Z]", "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DadosRecebedor that = (DadosRecebedor) o;
        return Objects.equals(nomeRecebedor, that.nomeRecebedor) &&
                Objects.equals(documentoRecebedor, that.documentoRecebedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeRecebedor, documentoRecebedor);
    }
}