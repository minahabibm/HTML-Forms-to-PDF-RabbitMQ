package com.example.simpleformstopdf;

import com.example.simpleformstopdf.htmlFormModal.FormProperties;
import com.example.simpleformstopdf.jsonPaw.JsonFileService;
import com.example.simpleformstopdf.rabbitMQ.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQController {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQController.class);

    @Autowired
    private JsonFileService tasksList;

    @Autowired
    Receiver rabbitMQTaskReceiver;


    @RabbitListener(queues = "${htmlToPdf.queue.name}")
    public void recievedTask(final FormProperties incomingMessageWTask) {

        logger.info("Received message: {} from htmlToPdf queue.", incomingMessageWTask);
        tasksList.setProgress(incomingMessageWTask.getId(), 20);

        try {
            rabbitMQTaskReceiver.receiveMessageWTask(incomingMessageWTask);
        } catch(Exception e) {
            logger.error("server error occurred Bypassing message requeue {}", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }

    }
}
