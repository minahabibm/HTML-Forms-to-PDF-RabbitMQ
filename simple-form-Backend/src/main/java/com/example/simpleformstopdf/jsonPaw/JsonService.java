package com.example.simpleformstopdf.jsonPaw;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

public interface JsonService {

    void loadAll() throws IOException;

    Hashtable<String, List<PdfProperties>> getFilesList() throws IOException;

    void addToFile() throws IOException;

    void addPdfToFile(PdfProperties newTask);

    int getTaskIndex(String Uid);

    String getName(String Uid);

    int getProgress(String Uid);

    void setProgress(String Uid, int progress);

    boolean getState(String Uid);

    void setState(String Uid, boolean state);

    String getDownloadPdF(String Uid);

    void setDownloadPdf(String Uid, String url);

    void deleteFromFile(String Uid) throws IOException;

    void init();

}
