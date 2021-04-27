package com.example.simpleformstopdf.uploadfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.simpleformstopdf.uploadfiles.storage.StorageFileNotFoundException;
import com.example.simpleformstopdf.uploadfiles.storage.StorageService;


@Controller
@CrossOrigin("*")
public class FileUploadController {

    private final StorageService storageService;
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file) {

        storageService.store(file);
        logger.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
        // TODO Return imgae url.
//        System.out.print(storageService.load(file.getOriginalFilename()));
        return ResponseEntity.ok().build();

    }

    // TODO endpoint to load the image

    @DeleteMapping("/upload/{filename:.+}")
    public ResponseEntity serveFile(@PathVariable String filename) {

        storageService.delete(filename);
        logger.info(String.format("File name '%s' Deleted.", filename));
        return ResponseEntity.ok().build();

    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
