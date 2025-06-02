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
package com.wildace.roulette.actors;


import akka.actor.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wildace.roulette.domain.AdminClientData;
import com.wildace.roulette.domain.ClientData;
import com.wildace.roulette.domain.TableState;
import com.wildace.roulette.services.RouletteService;

import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;


public class AutoRouletteActor extends AbstractActorWithTimers {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static final String NAME = "auto-roulette-actor";
    public static final String PATH = "/usr/" + NAME;

    private final String tableId;
    private final String tableName;
    private final int limitId;
    private final RouletteService tableService;

    

    public static Props props(String tableId, String tableName, int limitId,
                              RouletteService tableService) {
        return Props.create(AutoRouletteActor.class, () -> new AutoRouletteActor(tableId, tableName, limitId, tableService));
    }

    public AutoRouletteActor(String tableId, String tableName, int limitId, RouletteService tableService) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.limitId = limitId;
        this.tableService = tableService;
    }

    @Override
    public void preStart() {
        getTimers().startSingleTimer("OpenTimerKey", new Open(), Duration.create(1, "second"));
        getTimers().startTimerWithFixedDelay("PollTick", new PollTick(), Duration.create(1, "second"));
        log.info("AutoRouletteActor started with tableId: {}, tableName: {}, limitId: {}", tableId, tableName, limitId);
    }

    @Override
    public void postStop() {
    	getTimers().cancel("OpenTimerKey");
		getTimers().cancel("PollTick");
		log.info("AutoRouletteActor stopped");
    }

    private long getRoundId(String game) {
        int gameId = Integer.parseInt(game);
        int week = LocalDateTime.now().getDayOfYear();
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        int second = LocalDateTime.now().getSecond() / 10;
        return Long.parseLong(String.format("%02d%03d%02d%02d%1d", gameId, week, hour, minute, second));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(Open.class, msg -> {
                getContext().become(tableStateIdle(new TableState()));
            })
            .match(PollTick.class, msg -> {
                // PollTick logic for CLOSED state
            })
            .matchAny(msg -> log.info("Stashing {} because I can't handle it in the CLOSED state", msg))
            .build();
    }

    private Receive tableStateIdle(TableState state) {
        return receiveBuilder()
            .match(AdminConnected.class, msg -> {
                TableState newState = handleAdminConnected(state, msg.adminIp, msg.actor, msg.client);
                getContext().become(tableStateIdle(newState));
            })
            .match(PlayerConnected.class, msg -> {
                TableState newState = handlePlayerConnected(state, msg.playerIp, msg.actor, msg.client);
                getContext().become(tableStateIdle(newState));
            })
            .match(PollTick.class, msg -> {
                // Handle PollTick logic for IDLE state
            })
            .matchAny(msg -> log.warn("Message Not Handled@TABLE_STATE_1_IDLE state"))
            .build();
    }

    private TableState handleAdminConnected(TableState state, String adminIp, ActorRef actor, ActorRef client) {
        return state;
    }

    private TableState handlePlayerConnected(TableState state, String playerIp, ActorRef actor, ActorRef client) {
		return state;	
    }

    // Add other handlers like handleAdminDisconnected, handleTopperConnected, etc.

    public static class Open {}
    public static class PollTick {}

    public static class AdminConnected {
        public final String adminIp;
        public final ActorRef actor;
        public final ActorRef client;

        public AdminConnected(String adminIp, ActorRef actor, ActorRef client) {
            this.adminIp = adminIp;
            this.actor = actor;
            this.client = client;
        }
    }

    public static class PlayerConnected {
        public final String playerIp;
        public final ActorRef actor;
        public final ActorRef client;

        public PlayerConnected(String playerIp, ActorRef actor, ActorRef client) {
            this.playerIp = playerIp;
            this.actor = actor;
            this.client = client;
        }
    }


}