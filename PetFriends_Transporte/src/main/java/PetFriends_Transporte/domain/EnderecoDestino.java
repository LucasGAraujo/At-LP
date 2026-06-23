package PetFriends_Transporte.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class EnderecoDestino {

    private String logradouro;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;

    public EnderecoDestino(String logradouro, String numero, String cep, String cidade, String estado) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new IllegalArgumentException("O CEP de destino é obrigatório para o transporte.");
        }
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnderecoDestino that = (EnderecoDestino) o;
        return Objects.equals(cep, that.cep) &&
                Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, numero);
    }
}