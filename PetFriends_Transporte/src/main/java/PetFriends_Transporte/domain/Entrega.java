package PetFriends_Transporte.domain;

import PetFriends_Transporte.domain.ENUM.StatusEntrega;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_entrega")
@Getter
@NoArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    @Embedded
    private DadosRecebedor dadosRecebedor;

    private LocalDateTime dataAtualizacao;

    public Entrega(Long pedidoId) {
        this.pedidoId = pedidoId;
        this.status = StatusEntrega.PREPARANDO_ROTA;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void despacharParaCliente() {
        if (this.status != StatusEntrega.PREPARANDO_ROTA) {
            throw new IllegalStateException("A entrega já está em andamento.");
        }
        this.status = StatusEntrega.EM_TRANSITO;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void registrarEntregaRealizada(DadosRecebedor recebedor) {
        if (this.status != StatusEntrega.EM_TRANSITO) {
            throw new IllegalStateException("A entrega precisa estar em trânsito para ser concluída.");
        }
        this.dadosRecebedor = recebedor;
        this.status = StatusEntrega.ENTREGUE;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void registrarExtravio() {
        this.status = StatusEntrega.EXTRAVIADO;
        this.dataAtualizacao = LocalDateTime.now();
    }
}