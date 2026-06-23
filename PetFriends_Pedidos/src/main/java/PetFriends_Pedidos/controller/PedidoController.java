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
        // Cria um pedido fictício
        Pedido pedido = new Pedido("João da Silva", "12345678900", "Rua das Flores", "Centro", "20000-000", "Rio", "RJ");
        pedido.adicionarItem(new ItemPedido("1234567890123", 2));

        // Salva no banco de dados
        repository.save(pedido);

        return ResponseEntity.ok("✅ Pedido teste gerado com sucesso! ID: " + pedido.getId());
    }

    // 2. Rota que simula o Pagamento (Dispara evento para o Almoxarifado)
    @PostMapping("/{id}/pagar")
    public ResponseEntity<String> simularPagamento(@PathVariable Long id) {
        orquestradorService.processarPagamentoConfirmado(id);
        return ResponseEntity.ok("💸 Pagamento confirmado! Evento enviado para a fila do Almoxarifado.");
    }

    // 3. Rota que simula o Despacho (Dispara evento para o Transporte)
    @PostMapping("/{id}/despachar")
    public ResponseEntity<String> simularDespacho(@PathVariable Long id) {
        orquestradorService.despacharPedido(id);
        return ResponseEntity.ok("🚚 Pedido despachado! Evento enviado para a fila do Transporte.");
    }
}