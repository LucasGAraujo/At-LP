package PetFriends_Pedidos.publisher;

import PetFriends_Pedidos.DTO.PedidoDespachadoDTO;
import PetFriends_Pedidos.DTO.PedidoProntoParaSeparacaoDTO;
import PetFriends_Pedidos.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public PedidoEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarPedidoFechado(PedidoProntoParaSeparacaoDTO payload) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_PEDIDOS,
                "pedido.evento.fechado",
                payload
        );
        System.out.println("🚀 Evento disparado: pedido.evento.fechado");
    }

    public void publicarPedidoDespachado(PedidoDespachadoDTO payload) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_PEDIDOS,
                "pedido.evento.despachado",
                payload
        );
        System.out.println("🚀 Evento disparado: pedido.evento.despachado");
    }
}