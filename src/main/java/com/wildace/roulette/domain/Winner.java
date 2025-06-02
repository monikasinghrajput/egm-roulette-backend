package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "winners")
public class Winner {

    @Id
    private String id;  // Optional MongoDB document ID

    private double winAmount;
    private String nickName;

    // Default constructor
    public Winner() {}

    // Parameterized constructor
    public Winner(double winAmount, String nickName) {
        this.winAmount = winAmount;
        this.nickName = nickName;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(double winAmount) {
        this.winAmount = winAmount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "winAmount=" + winAmount +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
