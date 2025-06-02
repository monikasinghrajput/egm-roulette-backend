package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wheelData")
public class WheelData {

    @Id
    private String id;  // Optional MongoDB document ID

    private boolean connected = false;
    private String status = "Disconnected";
    private int lastResult = 0;

    // Default constructor
    public WheelData() {}

    // Parameterized constructor
    public WheelData(boolean connected, String status, int lastResult) {
        this.connected = connected;
        this.status = status;
        this.lastResult = lastResult;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

    @Override
    public String toString() {
        return "WheelData{" +
                "connected=" + connected +
                ", status='" + status + '\'' +
                ", lastResult=" + lastResult +
                '}';
    }
}

