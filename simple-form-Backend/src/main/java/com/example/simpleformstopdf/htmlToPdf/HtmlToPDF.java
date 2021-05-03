package com.example.simpleformstopdf.htmlToPdf;

import com.example.simpleformstopdf.jsonPaw.JsonFileService;
import com.example.simpleformstopdf.storage.StorageProperties;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import java.io.IOException;

public class HtmlToPDF {
    private final StorageProperties properties;
    private final String pdfFile;
    private final String title;
    private final String name;
    private final String quote;
    private final String imageFile;
    private final JsonFileService tasksList;

    public HtmlToPDF(String pdfFile, String name, String title, String quote, String imageFile, JsonFileService tasksList) {
        this.tasksList = tasksList;
        this.properties = new StorageProperties();
        this.pdfFile = pdfFile;
        this.name = name;
        this.title = title;
        this.quote = quote;
        this.imageFile = imageFile;
    }

    public void createPdf() throws IOException, InterruptedException {
        tasksList.setProgress(pdfFile, 37);
        Thread.sleep(1000);

        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(properties.getPdfLocation()+ "/" + pdfFile + ".pdf");
        tasksList.setProgress(pdfFile, 45);
        Thread.sleep(1000);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        tasksList.setProgress(pdfFile, 55);
        Thread.sleep(1000);

        // Initialize document
        Document document = new Document(pdf);
        tasksList.setProgress(pdfFile, 66);
        Thread.sleep(1000);

        //Add Content to the document
        // Add image
        if(imageFile.length() > 0) {
            Image img = new Image(ImageDataFactory.create(properties.getUploadLocation() + "/" + imageFile));
            document.add(img);
            tasksList.setProgress(pdfFile, 77);
            Thread.sleep(1000);
        }

        Paragraph docTitle = new Paragraph(this.title).setBold();
        docTitle.setTextAlignment(TextAlignment.CENTER);
        document.add(docTitle);
        tasksList.setProgress(pdfFile, 85);
        Thread.sleep(1000);

        Paragraph docQuote = new Paragraph(this.quote).setItalic();
        docQuote.setTextAlignment(TextAlignment.CENTER);
        document.add(docQuote);
        tasksList.setProgress(pdfFile, 90);
        Thread.sleep(1000);

        Paragraph docName = new Paragraph("Created by: "+ this.name).setFontSize(7);
        docName.setTextAlignment(TextAlignment.CENTER);
        document.add(docName);
        tasksList.setProgress(pdfFile, 95);
        Thread.sleep(1000);

        //Close document
        document.close();
        tasksList.setProgress(pdfFile, 100);
        tasksList.setState(pdfFile, true);

    }



}
