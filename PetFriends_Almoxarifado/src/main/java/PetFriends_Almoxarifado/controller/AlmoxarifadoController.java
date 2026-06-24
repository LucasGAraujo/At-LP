package PetFriends_Almoxarifado.controller;

import PetFriends_Almoxarifado.DTO.PedidoProntoParaSeparacaoDTO;
import PetFriends_Almoxarifado.domain.ENUM.StatusPreparacao;
import PetFriends_Almoxarifado.domain.OrdemDePreparacao;
import PetFriends_Almoxarifado.repository.OrdemDePreparacaoRepository;
import PetFriends_Almoxarifado.publisher.AlmoxarifadoEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/almoxarifado")
public class AlmoxarifadoController {

    private final OrdemDePreparacaoRepository repository;
    private final AlmoxarifadoEventPublisher publisher;

    public AlmoxarifadoController(OrdemDePreparacaoRepository repository, AlmoxarifadoEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @GetMapping("/ordens/pendentes")
    public ResponseEntity<List<OrdemDePreparacao>> listarPendentes() {
        List<OrdemDePreparacao> pendentes = repository.findByStatus(StatusPreparacao.PENDENTE);
        return ResponseEntity.ok(pendentes);
    }

    @PostMapping("/ordens/{pedidoId}/concluir")
    public ResponseEntity<String> concluirSeparacao(@PathVariable Long pedidoId) {

        OrdemDePreparacao ordem = repository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new RuntimeException("Ordem de Preparação não encontrada para o Pedido: " + pedidoId));

        ordem.concluir();
        repository.save(ordem);

        List<PedidoProntoParaSeparacaoDTO.ItemSeparacaoDTO> itensDTO = ordem.getItens().stream()
                .map(item -> new PedidoProntoParaSeparacaoDTO.ItemSeparacaoDTO(
                        item.getCodigoDeBarras().getNumero(),
                        item.getQuantidade()))
                .collect(Collectors.toList());

        PedidoProntoParaSeparacaoDTO payload = new PedidoProntoParaSeparacaoDTO(
                String.valueOf(ordem.getPedidoId()),
                "PADRAO",
                itensDTO
        );

        publisher.publicarSeparacaoConcluida(payload);

        return ResponseEntity.ok(" Sucesso! A Ordem de Preparação do Pedido " + pedidoId + " foi concluída e o transporte avisado.");
    }
}