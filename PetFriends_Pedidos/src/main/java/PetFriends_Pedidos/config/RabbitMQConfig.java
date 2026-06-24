package PetFriends_Pedidos.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_PEDIDOS = "petfriends.pedidos.exchange";
    public static final String FILA_PEDIDOS_ALMOXARIFADO_SEPARADO = "pedidos.v1.almoxarifado-separado";
    public static final String ROUTING_KEY_ALMOXARIFADO_SEPARADO = "almoxarifado.evento.separado";
    @Bean
    public TopicExchange pedidosExchange() {
        return new TopicExchange(EXCHANGE_PEDIDOS);
    }
    @Bean
    public Queue filaAlmoxarifadoSeparado() {
        return QueueBuilder.durable(FILA_PEDIDOS_ALMOXARIFADO_SEPARADO).build();
    }

    @Bean
    public Binding bindingAlmoxarifadoSeparado(Queue filaAlmoxarifadoSeparado, TopicExchange pedidosExchange) {
        return BindingBuilder
                .bind(filaAlmoxarifadoSeparado)
                .to(pedidosExchange)
                .with(ROUTING_KEY_ALMOXARIFADO_SEPARADO);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}