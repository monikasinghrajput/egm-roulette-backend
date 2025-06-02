package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "initialData")
public class InitialData {

    @Id
    private String id;  // Optional MongoDB document ID

    private String tableId = "";
    private long roundId = 1L;
    private String gameType = "";
    private RouletteGameData data = new RouletteGameData();
    private String rouletteType = "";
    private String physicalTableId = "";

    public InitialData() {}

    public InitialData(String tableId, long roundId, String gameType, RouletteGameData data,
                       String rouletteType, String physicalTableId) {
        this.tableId = tableId;
        this.roundId = roundId;
        this.gameType = gameType;
        this.data = data;
        this.rouletteType = rouletteType;
        this.physicalTableId = physicalTableId;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public long getRoundId() {
        return roundId;
    }

    public void setRoundId(long roundId) {
        this.roundId = roundId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public RouletteGameData getData() {
        return data;
    }

    public void setData(RouletteGameData data) {
        this.data = data;
    }

    public String getRouletteType() {
        return rouletteType;
    }

    public void setRouletteType(String rouletteType) {
        this.rouletteType = rouletteType;
    }

    public String getPhysicalTableId() {
        return physicalTableId;
    }

    public void setPhysicalTableId(String physicalTableId) {
        this.physicalTableId = physicalTableId;
    }

    @Override
    public String toString() {
        return "InitialData{" +
                "tableId='" + tableId + '\'' +
                ", roundId=" + roundId +
                ", gameType='" + gameType + '\'' +
                ", data=" + data +
                ", rouletteType='" + rouletteType + '\'' +
                ", physicalTableId='" + physicalTableId + '\'' +
                '}';
    }
}

