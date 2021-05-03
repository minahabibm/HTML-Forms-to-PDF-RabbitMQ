package com.example.simpleformstopdf.rabbitMQ;

import com.example.simpleformstopdf.htmlFormModal.FormProperties;
import com.example.simpleformstopdf.htmlToPdf.HtmlToPDF;
import com.example.simpleformstopdf.jsonPaw.JsonFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private JsonFileService tasksList;

    public void receiveMessageWTask(final FormProperties formData) {

        logger.info("Started a Task with ID: {} from htmlToPDf queue.", formData.getId());
        HtmlToPDF newPdf = new HtmlToPDF(formData.getId(), formData.getName(), formData.getTitle(), formData.getQuote(), formData.getFile(), tasksList);
        tasksList.setProgress(formData.getId(), 25);
        try {
            newPdf.createPdf();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
