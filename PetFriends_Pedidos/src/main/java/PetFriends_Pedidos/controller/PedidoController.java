package PetFriends_Pedidos.controller;

import PetFriends_Pedidos.domain.ItemPedido;
import PetFriends_Pedidos.domain.Pedido;
import PetFriends_Pedidos.repository.PedidoRepository;
import PetFriends_Pedidos.service.PedidoOrquestradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoOrquestradorService orquestradorService;
    private final PedidoRepository repository;

    public PedidoController(PedidoOrquestradorService orquestradorService, PedidoRepository repository) {
        this.orquestradorService = orquestradorService;
        this.repository = repository;
    }

    @PostMapping("/gerar-teste")
    public ResponseEntity<String> gerarPedidoDeTeste() {
        Pedido pedido = new Pedido("João da Silva", "12345678900", "Rua das Flores", "Centro", "20000-000", "Rio", "RJ");
        pedido.adicionarItem(new ItemPedido("1234567890123", 2));
        repository.save(pedido);

        return ResponseEntity.ok("✅ Pedido teste gerado com sucesso! ID: " + pedido.getId());
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<String> simularPagamento(@PathVariable Long id) {
        orquestradorService.processarPagamentoConfirmado(id);
        return ResponseEntity.ok("💸 Pagamento confirmado! O Almoxarifado já foi notificado automaticamente para separar as mercadorias.");
    }

    @PostMapping("/{id}/despachar-manual-emergencia")
    public ResponseEntity<String> simularDespachoManual(@PathVariable Long id) {
        orquestradorService.despacharPedido(id);
        return ResponseEntity.ok("⚠️ Forçando despacho manual! Evento enviado direto para o Transporte.");
    }
}