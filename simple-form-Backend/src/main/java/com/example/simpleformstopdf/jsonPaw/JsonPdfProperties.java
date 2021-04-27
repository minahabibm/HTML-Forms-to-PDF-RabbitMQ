package com.example.simpleformstopdf.jsonPaw;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JsonPdfProperties {

    private UUID uuid = UUID.randomUUID();
    private String name;
    private int progress;
    private boolean isReady;
    private String Url;

    public JsonPdfProperties( String name, int progress, boolean isReady, String Url) {
        this.name = name;
        this.progress = progress;
        this.isReady = isReady;
        this.Url = Url;
    }

    public JsonPdfProperties() {}

    @JsonIgnore
    public UUID getUuid() {
        return uuid;
    }

    public UUID setUuid(UUID uuid) {
        return this.uuid = uuid;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @JsonIgnore
    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean isReady) {

        this.isReady = isReady;
    }

    @JsonIgnore
    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    @JsonAnyGetter
    public Map<String, ?> getForJson() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> attrMap = new HashMap<>();
        attrMap.put("name", name);
        attrMap.put("progress", progress);
        attrMap.put("isReady", isReady);
        attrMap.put("Url", Url);
        map.put(uuid.toString(), attrMap);
        return map;
    }

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
