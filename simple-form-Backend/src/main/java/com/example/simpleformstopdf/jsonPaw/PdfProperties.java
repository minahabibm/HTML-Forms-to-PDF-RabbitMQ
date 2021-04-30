package com.example.simpleformstopdf.jsonPaw;

import com.fasterxml.jackson.annotation.*;

import java.util.UUID;


public class PdfProperties {

    private UUID uuid = UUID.randomUUID();
    private String name;
    private int progress;
    private boolean isReady;
    private String Url;


    public PdfProperties(String name, int progress, boolean isReady, String Url) {
        this.name = name;
        this.progress = progress;
        this.isReady = isReady;
        this.Url = Url;
    }

    public PdfProperties() {}

    @JsonSetter("uuid")
    public UUID getUuid() {
        return uuid;
    }

    @JsonGetter("uuid")
    public UUID setUuid(UUID uuid) {
        return this.uuid = uuid;
    }

    @JsonSetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("progress")
    public int getProgress() {
        return progress;
    }

    @JsonSetter("progress")
    public void setProgress(int progress) {
        this.progress = progress;
    }

    @JsonGetter("isReady")
    public boolean isReady() {
        return isReady;
    }

    @JsonSetter("isReady")
    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }

    @JsonGetter("Url")
    public String getUrl() {
        return Url;
    }

    @JsonSetter("Url")
    public void setUrl(String Url) {
        this.Url = Url;
    }

//    @JsonAnyGetter
//    public Map<String, ?> getForJson() {
//        Map<String, Object> map = new HashMap<>();
//        Map<String, Object> attrMap = new HashMap<>();
//        attrMap.put("name", name);
//        attrMap.put("progress", progress);
//        attrMap.put("isReady", isReady);
//        attrMap.put("Url", Url);
//        map.put(uuid.toString(), attrMap);
//        return map;
//    }

//    @JsonAnySetter
//    public void set(String nameUUID, Object value) {
//        setUuid(UUID.fromString(nameUUID));
//        System.out.println(value);
////        setName("name");
////        setProgress(00);
////        setReady(false);
////        setUrl("");
//    }

    @Override
    public String toString() {
        return uuid.toString() +
                "{" +
                "name=" + name +
                ", progress='" + progress +
                ", isReady=" + isReady +
                ", Url=" + Url +
                '}';
    }
}
