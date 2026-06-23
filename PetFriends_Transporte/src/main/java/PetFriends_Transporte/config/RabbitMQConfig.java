package PetFriends_Transporte.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // 1. Fila exclusiva do microsserviço de Transporte
    public static final String FILA_TRANSPORTE_PEDIDO_DESPACHADO = "transporte.v1.pedido-despachado";

    // 2. A MESMA Exchange usada pelo Almoxarifado (Tópico central de Pedidos)
    public static final String EXCHANGE_PEDIDOS = "petfriends.pedidos.exchange";

    // 3. Routing Key específica para o momento do despacho
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
        // Pega as mensagens com a etiqueta de "despachado" e joga na fila do Transporte
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