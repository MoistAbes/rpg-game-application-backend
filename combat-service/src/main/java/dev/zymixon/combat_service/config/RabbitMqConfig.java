package dev.zymixon.combat_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String CHARACTER_QUEUE = "characterQueue";
    public static final String CHARACTER_EXCHANGE_NAME = "characterExchange";
    public static final String CHARACTER_ROUTING_KEY = "characterRoutingKey";

    //COMBAT ITEM DROP REQUEST
    private static final String COMBAT_REQUEST_QUEUE = "combatRequestQueue";
    private static final String COMBAT_REQUEST_EXCHANGE = "combatRequestExchange";
    private static final String COMBAT_REQUEST_ROUTING_KEY = "combatRequestRoutingKey";

    //  QUEUES
    @Bean
    public Queue characterResponseQueue() {
        return new Queue("characterResponseQueue", false); // Predefined response queue
    }

    @Bean
    public Queue characterQueue() {
        return new Queue(CHARACTER_QUEUE, false);
    }

    //EXCHANGE
    @Bean
    public TopicExchange characterExchange() {
        return new TopicExchange(CHARACTER_EXCHANGE_NAME);
    }

    //ROUTING
    @Bean
    public Binding characterBinding() {
        return BindingBuilder.bind(characterQueue()).to(characterExchange()).with(CHARACTER_ROUTING_KEY);
    }

    //COMBAT ITEM DROP REQUEST
    @Bean
    public Queue combatRequestQueue() {
        return new Queue(COMBAT_REQUEST_QUEUE, false);
    }
    @Bean
    public TopicExchange combatRequestExchange() {
        return new TopicExchange(COMBAT_REQUEST_EXCHANGE);
    }
    @Bean
    public Binding combatRequestBinding() {
        return BindingBuilder.bind(combatRequestQueue()).to(combatRequestExchange()).with(COMBAT_REQUEST_ROUTING_KEY);
    }


    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }





}
