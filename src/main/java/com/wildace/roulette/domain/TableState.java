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
package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TableState class represents the state of a roulette table.
 * It contains various data related to the game, including game data, wheel data,
 * configuration data, license data, and client/admin information.
 */
@Document(collection = "tableState")
public class TableState {

    @Id
    private String id;  // MongoDB document ID

    private long roundId = 1L;
    private RouletteGameData gameData = new RouletteGameData();
    private WheelData wheelData = new WheelData();
    private ConfigData configData = new ConfigData();
    private LicenseData licenseData = new LicenseData();

    // Using Map<String, AdminClientData> for admins
    private Map<String, AdminClientData> admins = new HashMap<>();

    // Using Map<String, ClientData> for clients and toppers
    private Map<String, ClientData> clients = new HashMap<>();
    private Map<String, ClientData> toppers = new HashMap<>();

    private String gameStatus = "CLOSED";
    private int autoPlayCounter = 0;
    private int bettingTick = 0;
    private int wheelSpinningTick = 0;
    private int timerTimeLeft = 0;

    // Default constructor
    public TableState() {}

    // Getters and setters for all fields

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getRoundId() {
        return roundId;
    }

    public void setRoundId(long roundId) {
        this.roundId = roundId;
    }

    public RouletteGameData getGameData() {
        return gameData;
    }

    public void setGameData(RouletteGameData gameData) {
        this.gameData = gameData;
    }

    public WheelData getWheelData() {
        return wheelData;
    }

    public void setWheelData(WheelData wheelData) {
        this.wheelData = wheelData;
    }

    public ConfigData getConfigData() {
        return configData;
    }

    public void setConfigData(ConfigData configData) {
        this.configData = configData;
    }

    public LicenseData getLicenseData() {
        return licenseData;
    }

    public void setLicenseData(LicenseData licenseData) {
        this.licenseData = licenseData;
    }

    public Map<String, AdminClientData> getAdmins() {
        return admins;
    }

    public void setAdmins(Map<String, AdminClientData> admins) {
        this.admins = admins;
    }

    public Map<String, ClientData> getClients() {
        return clients;
    }

    public void setClients(Map<String, ClientData> clients) {
        this.clients = clients;
    }

    public Map<String, ClientData> getToppers() {
        return toppers;
    }

    public void setToppers(Map<String, ClientData> toppers) {
        this.toppers = toppers;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getAutoPlayCounter() {
        return autoPlayCounter;
    }

    public void setAutoPlayCounter(int autoPlayCounter) {
        this.autoPlayCounter = autoPlayCounter;
    }

    public int getBettingTick() {
        return bettingTick;
    }

    public void setBettingTick(int bettingTick) {
        this.bettingTick = bettingTick;
    }

    public int getWheelSpinningTick() {
        return wheelSpinningTick;
    }

    public void setWheelSpinningTick(int wheelSpinningTick) {
        this.wheelSpinningTick = wheelSpinningTick;
    }

    public int getTimerTimeLeft() {
        return timerTimeLeft;
    }

    public void setTimerTimeLeft(int timerTimeLeft) {
        this.timerTimeLeft = timerTimeLeft;
    }

    @Override
    public String toString() {
        return "TableState{" +
                "roundId=" + roundId +
                ", gameData=" + gameData +
                ", wheelData=" + wheelData +
                ", configData=" + configData +
                ", licenseData=" + licenseData +
                ", admins=" + admins +
                ", clients=" + clients +
                ", toppers=" + toppers +
                ", gameStatus='" + gameStatus + '\'' +
                ", autoPlayCounter=" + autoPlayCounter +
                ", bettingTick=" + bettingTick +
                ", wheelSpinningTick=" + wheelSpinningTick +
                ", timerTimeLeft=" + timerTimeLeft +
                '}';
    }
    
    private static final Logger logger = LoggerFactory.getLogger(TableState.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss.SSS z");
    
    
}

