package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "winBets")
public class WinBet {

    @Id
    private String id;  // Optional MongoDB document ID

    private int winningIndex;
    private double winAmount;

    // Default constructor
    public WinBet() {}

    // Parameterized constructor
    public WinBet(int winningIndex, double winAmount) {
        this.winningIndex = winningIndex;
        this.winAmount = winAmount;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWinningIndex() {
        return winningIndex;
    }

    public void setWinningIndex(int winningIndex) {
        this.winningIndex = winningIndex;
    }

    public double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(double winAmount) {
        this.winAmount = winAmount;
    }

    @Override
    public String toString() {
        return "WinBet{" +
                "winningIndex=" + winningIndex +
                ", winAmount=" + winAmount +
                '}';
    }
}
