package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payouts")
public class Payout {

    @Id
    private String _id;  // Optional MongoDB document ID
    private String id;  // Optional custom ID

    private int straightUpBet = 35;
    private int splitBet = 17;
    private int streetBet = 11;
    private int cornerBet = 8;
    private int basketBet = 6;
    private int lineBet = 5;
    private int columnBet = 2;
    private int dozenBet = 2;
    private int outsideBet = 1;

    // Default constructor
    public Payout() {}

    // Parameterized constructor
    public Payout(int straightUpBet, int splitBet, int streetBet, int cornerBet,
                  int basketBet, int lineBet, int columnBet, int dozenBet, int outsideBet) {
        this.straightUpBet = straightUpBet;
        this.splitBet = splitBet;
        this.streetBet = streetBet;
        this.cornerBet = cornerBet;
        this.basketBet = basketBet;
        this.lineBet = lineBet;
        this.columnBet = columnBet;
        this.dozenBet = dozenBet;
        this.outsideBet = outsideBet;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStraightUpBet() {
        return straightUpBet;
    }

    public void setStraightUpBet(int straightUpBet) {
        this.straightUpBet = straightUpBet;
    }

    public int getSplitBet() {
        return splitBet;
    }

    public void setSplitBet(int splitBet) {
        this.splitBet = splitBet;
    }

    public int getStreetBet() {
        return streetBet;
    }

    public void setStreetBet(int streetBet) {
        this.streetBet = streetBet;
    }

    public int getCornerBet() {
        return cornerBet;
    }

    public void setCornerBet(int cornerBet) {
        this.cornerBet = cornerBet;
    }

    public int getBasketBet() {
        return basketBet;
    }

    public void setBasketBet(int basketBet) {
        this.basketBet = basketBet;
    }

    public int getLineBet() {
        return lineBet;
    }

    public void setLineBet(int lineBet) {
        this.lineBet = lineBet;
    }

    public int getColumnBet() {
        return columnBet;
    }

    public void setColumnBet(int columnBet) {
        this.columnBet = columnBet;
    }

    public int getDozenBet() {
        return dozenBet;
    }

    public void setDozenBet(int dozenBet) {
        this.dozenBet = dozenBet;
    }

    public int getOutsideBet() {
        return outsideBet;
    }

    public void setOutsideBet(int outsideBet) {
        this.outsideBet = outsideBet;
    }

    @Override
    public String toString() {
        return "Payout{" +
                "straightUpBet=" + straightUpBet +
                ", splitBet=" + splitBet +
                ", streetBet=" + streetBet +
                ", cornerBet=" + cornerBet +
                ", basketBet=" + basketBet +
                ", lineBet=" + lineBet +
                ", columnBet=" + columnBet +
                ", dozenBet=" + dozenBet +
                ", outsideBet=" + outsideBet +
                '}';
    }
}

