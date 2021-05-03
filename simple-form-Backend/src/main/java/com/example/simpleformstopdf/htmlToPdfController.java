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

import java.io.IOException;
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

        PdfProperties newTask = new PdfProperties(htmlToPdf.getFirstName() + htmlToPdf.getLastName(), 0, false,  htmlToPdf.getFile());
        htmlToPdf.setId(newTask.getUuid().toString());

        logger.info(String.format("Html to Pdf task"));
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
    public ResponseEntity<?> throu() throws IOException {

//        System.out.println(tasksList.getName("7038c61e-380d-4d24-a1ce-16f1e8091325"));
//        tasksList.setProgress("7038c61e-380d-4d24-a1ce-16f1e8091325", 75);
//        System.out.println(tasksList.getState("7038c61e-380d-4d24-a1ce-16f1e8091325"));
//        tasksList.setState("7038c61e-380d-4d24-a1ce-16f1e8091325", false);
//        System.out.println(tasksList.getDownloadPdF("7038c61e-380d-4d24-a1ce-16f1e8091325"));
//        tasksList.setDownloadPdf("7038c61e-380d-4d24-a1ce-16f1e8091325", "../../../../");
//        tasksList.deleteFromFile("aee3fc40-852d-4a42-8d41-4619a165d554");

        return ResponseEntity.ok().build();
    }

}
