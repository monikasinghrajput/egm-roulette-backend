package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "rouletteGameHistory")
public class RouletteGameHistory {

    @Id
    private String id;               // MongoDB document ID

    private String playerId;         // ID of the player who played the game
    private Date gameDate;           // Date and time when the game was played
    private String result;           // Result of the roulette spin (e.g., "Red", "Black", "17")
    private double betAmount;        // Amount bet by the player
    private String betType;          // Type of bet placed (e.g., "Straight", "Split", "Red")

    // Constructors
    public RouletteGameHistory() {}

    public RouletteGameHistory(String playerId, Date gameDate, String result, double betAmount, String betType) {
        this.playerId = playerId;
        this.gameDate = gameDate;
        this.result = result;
        this.betAmount = betAmount;
        this.betType = betType;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }
}
