package com.example.simpleformstopdf;

import com.example.simpleformstopdf.htmlFormModal.FormProperties;
import com.example.simpleformstopdf.jsonPaw.JsonFileService;
import com.example.simpleformstopdf.jsonPaw.PdfProperties;
import com.example.simpleformstopdf.rabbitMQ.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@CrossOrigin("*")
public class htmlToPdfController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private JsonFileService tasksList;

    @Autowired
    Sender rabbitMQSender;

    @PostMapping("/htmltopdf")
    public ResponseEntity<?> postBody(@RequestBody FormProperties htmlToPdf) {

        PdfProperties newTask = new PdfProperties(htmlToPdf.getTitle(), 0, false,  htmlToPdf.getFile());
        htmlToPdf.setId(newTask.getUuid().toString());

        logger.info("Html to Pdf task");
        tasksList.addPdfToFile(newTask);
        rabbitMQSender.sendMessageWTask(htmlToPdf);

//        Map<String, Object> response = new HashMap<>();
//        response.put("taskID", htmlToPdf.getId());

        return ResponseEntity.ok().build();

    }

    @GetMapping("/progress/{UID}")
    public ResponseEntity<?> getTaskProgress(@PathVariable String UID) {

        logger.info(String.format("File Progress '%s' requested.", UID));

        Map<String, Object> response = new HashMap<>();
        response.put("taskProgress", tasksList.getProgress(UID));
        response.put("taskStatus", tasksList.getState(UID));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<?> throu() {

        return ResponseEntity.ok().build();
    }

}
