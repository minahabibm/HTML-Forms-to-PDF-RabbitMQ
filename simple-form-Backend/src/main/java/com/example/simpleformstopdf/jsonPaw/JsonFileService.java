package com.example.simpleformstopdf.jsonPaw;

import com.example.simpleformstopdf.storage.StorageProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
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
    public void loadAll() {

        try {
            tempTasksList = Arrays.asList(objectMapper.readValue(tasksLocation.toFile(), PdfProperties[].class));
        } catch(Exception ex) {
            tempTasksList = Arrays.asList();
            ex.printStackTrace();
        } finally {
            tasksList = new ArrayList<>(tempTasksList);
        }
    }

    @Override
    public Hashtable<String, List<PdfProperties>> getFilesList() {
        this.loadAll();
        Hashtable<String, List<PdfProperties>> my_dict = new Hashtable<>();
        my_dict.put("filesList", this.tasksList);
        return my_dict;
    }

    @Override
    public void addToFile() {
        try {
            objectMapper.writeValue(tasksLocation.toFile(), tasksList);
//            tasksList.forEach(System.out::println);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void addPdfToFile(PdfProperties newTask) {

        try {

            this.loadAll();
            tasksList.add(newTask);
            this.addToFile();

        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public int getTaskIndex(String Uid){
        try {
            this.loadAll();
            for (int counter = 0; counter < tasksList.size(); counter++) {
                if (tasksList.get(counter).getUuid().toString().equals(Uid)) {
                    return counter;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public String getName(String Uid) {
//        System.out.println(tasksList.get(index).getUuid().toString());
        try {
            this.loadAll();
            int index = this.getTaskIndex(Uid);
            if (index >= 0) {
                return tasksList.get(index).getName();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return "item not found";
    }

    @Override
    public int getProgress(String Uid) {
        try {
            this.loadAll();
            int index = this.getTaskIndex(Uid);
            if (index >= 0) {
                return tasksList.get(index).getProgress();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return -1;

    }

    @Override
    public void setProgress(String Uid, int progress) {
        try {
            this.loadAll();
            int index = this.getTaskIndex(Uid);
            if (index >= 0) {
                tasksList.get(index).setProgress(progress);
                this.addToFile();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean getState(String Uid) {
        try {
            this.loadAll();
            int index = this.getTaskIndex(Uid);
            if (index >= 0) {
                return tasksList.get(index).isReady();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return false;

    }

    @Override
    public void setState(String Uid, boolean state) {
        try {
            this.loadAll();
            int index = this.getTaskIndex(Uid);
            if (index >= 0) {
                tasksList.get(index).setReady(state);
                this.addToFile();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getDownloadPdF(String Uid) {

        try {
            this.loadAll();
            int index = this.getTaskIndex(Uid);
            if (index >= 0) {
                return tasksList.get(index).getUrl();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return "";

    }

    @Override
    public void setDownloadPdf(String Uid, String url) {
        try {
            this.loadAll();
            int index = this.getTaskIndex(Uid);
            if (index >= 0) {
                tasksList.get(index).setUrl(url);
                this.addToFile();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteFromFile(String Uid) {

        try {
            this.loadAll();
            int index = this.getTaskIndex(Uid);
            if (index >= 0) {
                tasksList.remove(index);
                this.addToFile();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void init() {
    }

}
