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
package com.wildace.roulette.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wildace.roulette.actors.AutoRouletteActor;
import com.wildace.roulette.domain.documents.User;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import jakarta.annotation.PostConstruct;

@Service
public class RouletteService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ActorSystem actorSystem;

    private ActorRef actorAutoRoulette;
    
    //Define a Roulette Game Spin Number count which is accessable to controller
    private int rouletteSpinNumber = 1;
    

    /**
	 * Generates the next roulette spin number in a 6-digit format.
	 * The spin number is incremented after each call.
	 *
	 * @return A string representing the next roulette spin number, formatted as a 6-digit number.
	 */
    
    public String generateSpinNumber() {
    	String nextSpinNumber = String.format("%06d", rouletteSpinNumber);
		rouletteSpinNumber++;
		return nextSpinNumber;
	
	}
    
    

    public RouletteService(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }

    @PostConstruct
    public void init() {
        actorAutoRoulette = actorSystem.actorOf(
                AutoRouletteActor.props(
                        "221002",
                        "Auto Roulette",
                        758359,
                        this
                ), 
                "" + AutoRouletteActor.NAME + "221002" // Unique name for the actor
        );

		log.info("AutoRouletteActor started with actorRef: {}", actorAutoRoulette);
    }

    public ActorRef getAutoRouletteActor() {
        return actorAutoRoulette;
    }


}
