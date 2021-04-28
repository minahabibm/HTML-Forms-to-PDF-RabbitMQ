package com.example.simpleformstopdf;

import com.example.simpleformstopdf.jsonPaw.JsonFileService;
import com.example.simpleformstopdf.jsonPaw.PdfProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.simpleformstopdf.storage.StorageFileNotFoundException;
import com.example.simpleformstopdf.storage.StorageService;

import java.io.IOException;


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
//    @GetMapping("/pdfs")

    // TODO Add a GET endpoint to download requested file.
//    @GetMapping("/download/{UID}")

    //  TODO Remove uid from the storage file and handle errors.
    @DeleteMapping("/pdf/{UID}")
    public ResponseEntity serveFile(@PathVariable String UID) {
        storageService.delete(UID, "pdf");
        logger.info(String.format("File name '%s' Deleted.", UID));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public ResponseEntity throu() throws IOException {

        JsonFileService tasksList = new JsonFileService();
        tasksList.addPdfToFile("Doe", 50, true, "../../");
        tasksList.deleteFromFile("95da723f-246f-4745-b5fc-52a4bdd5d2b9");

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
