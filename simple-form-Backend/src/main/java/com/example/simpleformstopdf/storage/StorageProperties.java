package com.example.simpleformstopdf.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "storage";
    private String uploadLocation = "upload-dir";
    private String pdfLocation = "pdf-dir";
    private String fileName = "tasks.json";

    public String getLocation() {
        return location;
    }
    public String getUploadLocation() {
        return location + "/" + uploadLocation  ;
    }
    public String getPdfLocation() {
        return location + "/" + pdfLocation  ;
    }
    public String getTasksLocation() {
        return location + "/" + fileName  ;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
