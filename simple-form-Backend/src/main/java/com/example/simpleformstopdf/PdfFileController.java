package com.example.simpleformstopdf;

import com.example.simpleformstopdf.jsonPaw.JsonFileService;
import com.example.simpleformstopdf.storage.StorageFileNotFoundException;
import com.example.simpleformstopdf.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin("*")
public class PdfFileController {

    private final StorageService storageService;
    private final JsonFileService tasksList ;
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    public PdfFileController(StorageService storageService) {
        this.storageService = storageService;
        this.tasksList = new JsonFileService();
    }

    @GetMapping("/pdfs")
    public ResponseEntity<?> getFilesList() {
        logger.info("List of Tasks Requested ");
        return ResponseEntity.ok(tasksList.getFilesList());
    }

    @GetMapping("/download/{UID}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String UID) {

        Resource file = storageService.loadAsResource(UID + ".pdf", "pdf");
        logger.info(String.format("File name '%s' Downloaded.", UID));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @DeleteMapping("/pdfs/{UID}")
    public ResponseEntity<?> deleteFile(@PathVariable String UID) {

        tasksList.deleteFromFile(UID);
        storageService.delete(UID + ".pdf", "pdf");
        logger.info(String.format("File name '%s' Deleted.", UID));
        return ResponseEntity.ok().build();

    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
