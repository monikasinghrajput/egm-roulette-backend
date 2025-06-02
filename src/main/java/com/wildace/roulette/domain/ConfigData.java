package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "configData")
public class ConfigData {

    @Id
    private String id;  // Optional MongoDB document ID

    private TableLimit tableLimit = new TableLimit();
    private Boolean isOppositeBettingAllowed = false;

    // Default constructor
    public ConfigData() {}

    public ConfigData(TableLimit tableLimit,
                      boolean isOppositeBettingAllowed) {
        this.tableLimit = tableLimit;
        this.isOppositeBettingAllowed = isOppositeBettingAllowed;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TableLimit getTableLimit() {
        return tableLimit;
    }

    public void setTableLimit(TableLimit tableLimit) {
        this.tableLimit = tableLimit;
    }

    
    public boolean isOppositeBettingAllowed() {
        return isOppositeBettingAllowed;
    }

    public void setOppositeBettingAllowed(boolean oppositeBettingAllowed) {
        isOppositeBettingAllowed = oppositeBettingAllowed;
    }

    @Override
    public String toString() {
        return "ConfigData{" +
                "tableLimit=" + tableLimit +
                ", isOppositeBettingAllowed=" + isOppositeBettingAllowed +
                '}';
    }
}

