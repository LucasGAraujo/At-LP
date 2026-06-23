package PetFriends_Transporte.LISTENER;


import PetFriends_Transporte.DTO.PedidoDespachadoDTO;
import PetFriends_Transporte.config.RabbitMQConfig;
import PetFriends_Transporte.service.TransporteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoDespachadoListener {

    private final TransporteService transporteService;

    public PedidoDespachadoListener(TransporteService transporteService) {
        this.transporteService = transporteService;
    }

    @RabbitListener(queues = RabbitMQConfig.FILA_TRANSPORTE_PEDIDO_DESPACHADO)
    public void receberEventoPedidoDespachado(PedidoDespachadoDTO payload) {
        try {
            transporteService.roteirizarEntrega(payload);
        } catch (Exception e) {
            System.err.println("Erro ao processar roteirização do pedido " + payload.pedidoId() + ": " + e.getMessage());
            throw e;
        }
    }
}