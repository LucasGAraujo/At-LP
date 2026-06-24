package PetFriends_Transporte.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_TRANSPORTE_PEDIDO_DESPACHADO = "transporte.v1.pedido-despachado";

    public static final String EXCHANGE_PEDIDOS = "petfriends.pedidos.exchange";

    public static final String ROUTING_KEY_PEDIDO_DESPACHADO = "pedido.evento.despachado";

    @Bean
    public Queue filaPedidoDespachado() {
        return QueueBuilder.durable(FILA_TRANSPORTE_PEDIDO_DESPACHADO).build();
    }

    @Bean
    public TopicExchange pedidosExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_PEDIDOS).durable(true).build();
    }

    @Bean
    public Binding bindingPedidoDespachado(Queue filaPedidoDespachado, TopicExchange pedidosExchange) {
        return BindingBuilder
                .bind(filaPedidoDespachado)
                .to(pedidosExchange)
                .with(ROUTING_KEY_PEDIDO_DESPACHADO);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}