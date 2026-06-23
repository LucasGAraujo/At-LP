package PetFriends_Almoxarifado.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {


    public static final String FILA_ALMOXARIFADO_PEDIDO_FECHADO = "almoxarifado.v1.pedido-fechado";

    public static final String EXCHANGE_PEDIDOS = "petfriends.pedidos.exchange";

    public static final String ROUTING_KEY_PEDIDO_FECHADO = "pedido.evento.fechado";

    @Bean
    public Queue filaPedidoFechado() {
        return QueueBuilder
                .durable(FILA_ALMOXARIFADO_PEDIDO_FECHADO)
                .build();
    }


    @Bean
    public TopicExchange pedidosExchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE_PEDIDOS)
                .durable(true)
                .build();
    }

    @Bean
    public Binding bindingPedidoFechado(Queue filaPedidoFechado, TopicExchange pedidosExchange) {
        return BindingBuilder
                .bind(filaPedidoFechado)
                .to(pedidosExchange)
                .with(ROUTING_KEY_PEDIDO_FECHADO);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}