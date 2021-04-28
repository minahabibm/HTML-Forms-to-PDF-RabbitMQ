package com.example.simpleformstopdf.jsonPaw;

import java.io.IOException;

public interface JsonService {


    void loadAll() throws IOException;

    void addToFile() throws IOException;

    void addPdfToFile(String taskName, int taskProgress, boolean taskIsReady, String taskUrl ) throws IOException;

    void getName(String Uid);

    void getProgress(String Uid);

    void setProgress(String Uid);

    void getState(String Uid);

    void setState(String Uid);

    void getDownloadPdF(String Uid);

    void setDownloadPdf(String Uid);

    void deleteFromFile(String Uid) throws IOException;

    void init();

}
