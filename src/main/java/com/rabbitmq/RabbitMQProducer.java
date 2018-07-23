package com.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.springboot.utils.ServiceUtil;
 
@Service
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;
 
    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
 
    public void sendEvent(final String employeeUuid, 
			  final String serviceMessage, final String date) 
    {
      this.rabbitTemplate.convertAndSend(RabbitMQConfiguration.QUEUE_EMPLOYEE_EVENTS, 
      		ServiceUtil.getJsonObjectAsString(employeeUuid, serviceMessage, date));
    }
}