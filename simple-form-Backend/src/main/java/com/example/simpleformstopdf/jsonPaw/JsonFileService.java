package com.example.simpleformstopdf.jsonPaw;

import com.example.simpleformstopdf.storage.StorageProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFileService implements JsonService {

    private final StorageProperties properties;
    private final Path tasksLocation ;
    private final ObjectMapper objectMapper;
    private List<PdfProperties> tempTasksList;
    private List<PdfProperties> tasksList ;


    @Autowired
    public JsonFileService() {
        this.properties = new StorageProperties();
        this.tasksLocation = Paths.get(properties.getTasksLocation());
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void loadAll() throws IOException {

        try {

            tempTasksList = Arrays.asList(objectMapper.readValue(tasksLocation.toFile(), PdfProperties[].class));
            tasksList = new ArrayList<>(tempTasksList);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void addToFile() throws IOException {
        try {
            objectMapper.writeValue(tasksLocation.toFile(), tasksList);
//            tasksList.forEach(System.out::println);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void addPdfToFile(String taskName, int taskProgress, boolean taskIsReady, String taskUrl) {

        try {

            this.loadAll();
            PdfProperties temp = new PdfProperties(taskName, taskProgress, taskIsReady, taskUrl);
            tasksList.add(temp);
            this.addToFile();

        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void getName(String Uid) {

    }

    @Override
    public void getProgress(String Uid) {

    }

    @Override
    public void setProgress(String Uid) {

    }

    @Override
    public void getState(String Uid) {

    }

    @Override
    public void setState(String Uid) {

    }

    @Override
    public void getDownloadPdF(String Uid) {

    }

    @Override
    public void setDownloadPdf(String Uid) {

    }

    @Override
    public void deleteFromFile(String Uid) throws IOException {

        try {
            this.loadAll();
            for (int counter = 0; counter < tasksList.size(); counter++) {
                if (tasksList.get(counter).getUuid().toString().equals(Uid)) {
                    System.out.println(tasksList.get(counter).getUuid().toString());
                    tasksList.remove(counter);
                    this.addToFile();
                    break;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void init() {
    }

}
