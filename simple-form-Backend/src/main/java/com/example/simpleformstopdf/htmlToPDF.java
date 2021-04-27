package com.example.simpleformstopdf;

import com.example.simpleformstopdf.jsonPaw.JsonPdfProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.simpleformstopdf.storage.StorageFileNotFoundException;
import com.example.simpleformstopdf.storage.StorageService;


@Controller
@CrossOrigin("*")
public class htmlToPDF {

    private final StorageService storageService;
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    public htmlToPDF(StorageService storageService) {
        this.storageService = storageService;
    }

    // TODO Add a POST endpoint to accept the form.
//    @PostMapping("/htmltopdf")

    // TODO Add a GET endpoint to Check on Progress.
//    @GetMapping("/progress/{UID}")

    // TODO Add a GET endpoint to return a list with all availble files.
    @GetMapping("/pdfs")
    public ResponseEntity throu() throws JsonProcessingException {
        JsonPdfProperties johnDoe = new JsonPdfProperties("John", 95, false, "01");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(johnDoe);

        System.out.println(json);
        return ResponseEntity.ok().build();
    }

    // TODO Add a GET endpoint to download requested file.
//    @GetMapping("/download/{UID}")

    //  TODO Remove uid from the storage file and handle errors.
    @DeleteMapping("/pdf/{UID}")
    public ResponseEntity serveFile(@PathVariable String UID) {
        storageService.delete(UID, "pdf");
        logger.info(String.format("File name '%s' Deleted.", UID));
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
