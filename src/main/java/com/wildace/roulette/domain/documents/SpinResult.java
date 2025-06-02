/*
 * Copyright 2024 Wildace Private Limited - All Rights Reserved
 *
 * Licensed under Wildace Software License Agreement ("License").
 * You may not use this file except in compliance with the License.
 *
 * NOTICE
 * ALL INFORMATION CONTAINED HEREIN IS, AND REMAINS THE PROPERTY OF WILDACE PRIVATE LIMITED.
 * THE INTELLECTUAL AND TECHNICAL CONCEPTS CONTAINED HEREIN ARE PROPRIETARY TO WILDACE PRIVATE LIMITED AND ARE PROTECTED BY TRADE SECRET OR COPYRIGHT LAW.
 * DISSEMINATION OF THIS INFORMATION OR REPRODUCTION OF THIS MATERIAL IS STRICTLY FORBIDDEN UNLESS PRIOR WRITTEN PERMISSION IS OBTAINED FROM WILDACE PRIVATE LIMITED.
 * **********************************************************************************************************************************************************************
 * Change History
 * **********************************************************************************************************************************************************************
 * |     Date      |     Name     |      Change     |      Details
 * |  15/05/2025   | Wilson Sam   |     Created     |  File Creation
 * **********************************************************************************************************************************************************************
 * */
package com.wildace.roulette.domain.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "roulette_results")
public class SpinResult {

    @Id
    private String id;
    private String egmId;
    private String uid;
    private double wallet;
    private String spinNumber;
    private List<Bet> betsList;
    private List<WonBet> wonBetsList;
    private double betAmount;
    private double winAmount;
    private double oldCredit;
    private double newCredit;
    private Integer number;
    private String spinStart;
    private String spinEnd;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEgmId() {
        return egmId;
    }

    public void setEgmId(String egmId) {
        this.egmId = egmId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public String getSpinNumber() {
        return spinNumber;
    }

    public void setSpinNumber(String spinNumber) {
        this.spinNumber = spinNumber;
    }

    public List<Bet> getBetsList() {
        return betsList;
    }

    public void setBetsList(List<Bet> betsList) {
        this.betsList = betsList;
    }

    public List<WonBet> getWonBetsList() {
        return wonBetsList;
    }

    public void setWonBetsList(List<WonBet> wonBetsList) {
        this.wonBetsList = wonBetsList;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(double winAmount) {
        this.winAmount = winAmount;
    }

    public double getOldCredit() {
        return oldCredit;
    }

    public void setOldCredit(double oldCredit) {
        this.oldCredit = oldCredit;
    }

    public double getNewCredit() {
        return newCredit;
    }

    public void setNewCredit(double newCredit) {
        this.newCredit = newCredit;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSpinStart() {
        return spinStart;
    }

    public void setSpinStart(String spinStart) {
        this.spinStart = spinStart;
    }

    public String getSpinEnd() {
        return spinEnd;
    }

    public void setSpinEnd(String spinEnd) {
        this.spinEnd = spinEnd;
    }

    // Inner Classes for Bet and WonBet
    public static class Bet {
        private Integer betIndex;
        private double betAmount;

        public Integer getBetIndex() {
            return betIndex;
        }

        public void setBetIndex(Integer betIndex) {
            this.betIndex = betIndex;
        }

        public double getBetAmount() {
            return betAmount;
        }

        public void setBetAmount(double betAmount) {
            this.betAmount = betAmount;
        }
    }

    public static class WonBet {
        private Integer betIndex;
        private double betAmount;
        private double winAmount;
        private String name;

        public Integer getBetIndex() {
            return betIndex;
        }

        public void setBetIndex(Integer betIndex) {
            this.betIndex = betIndex;
        }

        public double getBetAmount() {
            return betAmount;
        }

        public void setBetAmount(double betAmount) {
            this.betAmount = betAmount;
        }

        public double getWinAmount() {
            return winAmount;
        }

        public void setWinAmount(double winAmount) {
            this.winAmount = winAmount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
