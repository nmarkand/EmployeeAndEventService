package com.rabbitmq;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class RabbitMQConfiguration  
{
    public static final String QUEUE_EMPLOYEE_EVENTS = "employee.event";
    public static final String EXCHANGE_EVENTS = "event.exchange";
 
    @Bean
    Queue eventsQueue() {
        return QueueBuilder.durable(QUEUE_EMPLOYEE_EVENTS).build();
    }
 
    @Bean
    Exchange eventsExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_EVENTS).build();
    }
 
    @Bean
    Binding binding(Queue eventQueue, TopicExchange eventExchange) {
        return BindingBuilder.bind(eventQueue).to(eventExchange).with(QUEUE_EMPLOYEE_EVENTS);
    }
}