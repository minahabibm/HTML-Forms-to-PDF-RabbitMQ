package com.example.simpleformstopdf.jsonPaw;

public interface JsonService {

    void init();

    void addPdfToFile();

    void loadAll();

    void getName(String Uid);

    void getProgress(String Uid);

    void setProgress(String Uid);

    void getState(String Uid);

    void setState(String Uid);

    void getDownloadPdF(String Uid);

    void setDownloadPdf(String Uid);

    void deleteFromFile(String Uid);

}
