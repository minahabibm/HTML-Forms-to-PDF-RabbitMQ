package com.example.simpleformstopdf.rabbitMQ;

import com.example.simpleformstopdf.htmlFormModal.FormProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private rabbitMQProperties rabbitMQConfigReader;

    public void sendMessageWTask(final FormProperties data) {
        logger.info("Sending message with task to the queue using routingKey {}. Message= {}", rabbitMQConfigReader.getHtmlToPdfRoutingKey(), data);
        rabbitTemplate.convertAndSend(rabbitMQConfigReader.getHtmlToPdfExchange(), rabbitMQConfigReader.getHtmlToPdfRoutingKey(), data);
        logger.info("The message with task has been sent to the queue.");
    }

}
