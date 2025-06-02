package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wins")
public class Win {

    @Id
    private String id;  // Optional MongoDB document ID

    private int winningNumber;
    private long roundId;

    // Default constructor
    public Win() {}

    // Parameterized constructor
    public Win(int winningNumber, long roundId) {
        this.winningNumber = winningNumber;
        this.roundId = roundId;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(int winningNumber) {
        this.winningNumber = winningNumber;
    }

    public long getRoundId() {
        return roundId;
    }

    public void setRoundId(long roundId) {
        this.roundId = roundId;
    }

    // Override toString() to match Scala's string representation
    @Override
    public String toString() {
        return roundId + " -> " + winningNumber;
    }
}

