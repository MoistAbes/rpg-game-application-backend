package dev.zymixon.inventory_service.config;

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
public class RabbitMQConfig {

    public static final String GENERAL_QUEUE = "exampleQueue";

    public static final String HELMET_QUEUE = "helmetQueue";
    public static final String CHEST_QUEUE = "chestQueue";
    public static final String GLOVES_QUEUE = "glovesQueue";
    public static final String BOOTS_QUEUE = "bootsQueue";
    public static final String WEAPON_QUEUE = "weaponQueue";

    public static final String EXCHANGE_NAME = "exampleExchange";

    public static final String GENERAL_ROUTING_KEY = "exampleRoutingKey";
    public static final String HELMET_ROUTING_KEY = "helmetRoutingKey";
    public static final String CHEST_ROUTING_KEY = "chestRoutingKey";
    public static final String GLOVES_ROUTING_KEY = "glovesRoutingKey";
    public static final String BOOTS_ROUTING_KEY = "bootsRoutingKey";
    public static final String WEAPON_ROUTING_KEY = "weaponRoutingKey";

    //COMBAT ITEM DROP REQUEST
    private static final String COMBAT_REQUEST_QUEUE = "combatRequestQueue";
    private static final String COMBAT_REQUEST_EXCHANGE = "combatRequestExchange";
    private static final String COMBAT_REQUEST_ROUTING_KEY = "combatRequestRoutingKey";



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

    //  QUEUES
    @Bean
    public Queue responseQueue() {
        return new Queue("responseQueue", false); // Predefined response queue
    }

    @Bean
    public Queue generalQueue() {
        return new Queue(GENERAL_QUEUE, false);
    }

    @Bean
    public Queue helmetQueue() {
        return new Queue(HELMET_QUEUE, false);
    }

    @Bean
    public Queue chestQueue() {
        return new Queue(CHEST_QUEUE, false);
    }

    @Bean
    public Queue glovesQueue() {
        return new Queue(GLOVES_QUEUE, false);
    }

    @Bean
    public Queue bootsQueue() {
        return new Queue(BOOTS_QUEUE, false);
    }
    @Bean
    public Queue weaponQueue() {
        return new Queue(WEAPON_QUEUE, false);
    }


    //EXCHANGE
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    //ROUTING
    @Bean
    public Binding generalBinding() {
        return BindingBuilder.bind(generalQueue()).to(exchange()).with(GENERAL_ROUTING_KEY);
    }

    @Bean
    public Binding helmetBinding() {
        return BindingBuilder.bind(helmetQueue()).to(exchange()).with(HELMET_ROUTING_KEY);
    }

    @Bean
    public Binding chestBinding() {
        return BindingBuilder.bind(chestQueue()).to(exchange()).with(CHEST_ROUTING_KEY);
    }

    @Bean
    public Binding glovesBinding() {
        return BindingBuilder.bind(glovesQueue()).to(exchange()).with(GLOVES_ROUTING_KEY);
    }

    @Bean
    public Binding bootsBinding() {
        return BindingBuilder.bind(bootsQueue()).to(exchange()).with(BOOTS_ROUTING_KEY);
    }

    @Bean
    public Binding weaponBinding() {
        return BindingBuilder.bind(weaponQueue()).to(exchange()).with(WEAPON_ROUTING_KEY);
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


}
