package com.example.simpleformstopdf.rabbitMQ;

import com.example.simpleformstopdf.htmlFormModal.FormProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    public void receiveMessageWTaskForApp1(final FormProperties formData) {

        logger.info("Started a Task with ID: {} from htmlToPDf queue.", formData.getId());

    }
}
