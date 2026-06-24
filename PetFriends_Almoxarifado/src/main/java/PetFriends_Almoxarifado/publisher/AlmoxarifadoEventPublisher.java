package PetFriends_Almoxarifado.publisher;

import PetFriends_Almoxarifado.DTO.PedidoProntoParaSeparacaoDTO;
import PetFriends_Almoxarifado.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AlmoxarifadoEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public AlmoxarifadoEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarSeparacaoConcluida(PedidoProntoParaSeparacaoDTO payload) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_PEDIDOS,
                "almoxarifado.evento.separado",
                payload
        );
        System.out.println("📦 Almoxarifado: Pedido " + payload.pedidoId() + " separado com sucesso!");
    }
}
