package com.rabbitmq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rest.springboot.event.repositories.IEventServiceRepository;
 
@Component
public class RabbitMQConsumer 
{
    static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
    
    public static final String QUEUE_EMPLOYEE_EVENTS = "employee.event";
    
    @Autowired
    IEventServiceRepository iEventServiceRepository;
 
    @RabbitListener(queues = QUEUE_EMPLOYEE_EVENTS)
    public void processEvent(final String message) 
    {
        logger.info("Event Received: " + message);
              
        iEventServiceRepository.createEvent(message);              
    }
}