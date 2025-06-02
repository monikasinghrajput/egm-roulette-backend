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


import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildace.roulette.controller.RouletteAPI2Controller;
import com.wildace.roulette.domain.documents.BetLimitsDocument;
import com.wildace.roulette.repositories.BetLimitsRepository;
import jakarta.annotation.PostConstruct;


import java.util.List;
import java.util.Optional;

@Service
public class BetLimitsService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BetLimitsService.class);
	
	public static final String BET_LIMITS_JSON = """
			{
			  "id": 1,
			  "limits": [
			    {
			      "category": "General Bet Limits",
			      "limits": [
			        { "id": 1, "name": "Min Bet", "amount": 10 },
			        { "id": 2, "name": "Max Bet", "amount": 5000 }
			      ]
			    },
			    {
			      "category": "Straight Up Bets",
			      "limits": [
			        { "id": 3, "name": "Min Straight Up Bet", "amount": 10 },
			        { "id": 4, "name": "Max Straight Up Bet", "amount": 1500 }
			      ]
			    },
			    {
			      "category": "Split Bets",
			      "limits": [
			        { "id": 5, "name": "Min Split Bet", "amount": 10 },
			        { "id": 6, "name": "Max Split Bet", "amount": 2000 }
			      ]
			    },
			    {
			      "category": "Street Bets",
			      "limits": [
			        { "id": 7, "name": "Min Street Bet", "amount": 10 },
			        { "id": 8, "name": "Max Street Bet", "amount": 2500 }
			      ]
			    },
			    {
			      "category": "Corner Bets",
			      "limits": [
			        { "id": 9, "name": "Min Corner Bet", "amount": 10 },
			        { "id": 10, "name": "Max Corner Bet", "amount": 3000 }
			      ]
			    },
			    {
			      "category": "Line Bets",
			      "limits": [
			        { "id": 11, "name": "Min Line Bet", "amount": 10 },
			        { "id": 12, "name": "Max Line Bet", "amount": 2000 }
			      ]
			    },
			    {
			      "category": "Column Bets",
			      "limits": [
			        { "id": 13, "name": "Min Column Bet", "amount": 10 },
			        { "id": 14, "name": "Max Column Bet", "amount": 1500 }
			      ]
			    },
			    {
			      "category": "Dozen Bets",
			      "limits": [
			        { "id": 15, "name": "Min Dozen Bet", "amount": 10 },
			        { "id": 16, "name": "Max Dozen Bet", "amount": 2000 }
			      ]
			    },
			    {
			      "category": "Outside Bets",
			      "limits": [
			        { "id": 17, "name": "Min Outside Bet", "amount": 10 },
			        { "id": 18, "name": "Max Outside Bet", "amount": 4000 }
			      ]
			    }
			  ]
			}
			""";
	
    private final BetLimitsRepository betLimitsRepository;

    public BetLimitsService(BetLimitsRepository betLimitsRepository) {
        this.betLimitsRepository = betLimitsRepository;
    }

    // Create a new BetLimitsDocument
    public BetLimitsDocument createBetLimits(BetLimitsDocument betLimitsDocument) {
		return betLimitsRepository.save(betLimitsDocument);
	}

    // Retrieve all BetLimitsDocuments
    public List<BetLimitsDocument> getAllBetLimits() {
        return betLimitsRepository.findAll();
    }

    // Retrieve a specific BetLimitsDocument by ID
    public Optional<BetLimitsDocument> getBetLimitsById(String id) {
        return betLimitsRepository.findById(id);
    }

	// Update an existing BetLimitsDocument
    public BetLimitsDocument updateBetLimits(String id, BetLimitsDocument updatedBetLimitsDocument) {
    			return betLimitsRepository.findById(id)
				.map(betLimitsDocument -> {
					betLimitsDocument.setLimits(updatedBetLimitsDocument.getLimits());
					return betLimitsRepository.save(betLimitsDocument);
				})
				.orElseThrow(() -> new RuntimeException("Bet limits document not found"));
    		
    }
    
    
    // Delete a BetLimitsDocument by ID
    public void deleteBetLimitsById(String id) {
        betLimitsRepository.deleteById(id);
    }

    // Retrieve BetLimitsDocuments by Category Name
    public List<BetLimitsDocument> getBetLimitsByCategory(String category) {
        return betLimitsRepository.findByLimitsCategory(category);
    }

    // Retrieve BetLimitsDocuments by Limit Name
    public List<BetLimitsDocument> getBetLimitsByLimitName(String limitName) {
        return betLimitsRepository.findByLimitsLimitsName(limitName);
    }
    
    @PostConstruct
    public void insertDefaultBetLimitsIfMissing() throws JsonMappingException, JsonProcessingException {
        // Example: Check if any BetLimitsDocument exists; if not, insert default.
        if (betLimitsRepository.count() == 0) {
        	log.info("No BetLimitsDocument found, inserting default limits.");
        	
        	ObjectMapper objectMapper = new ObjectMapper();

        	BetLimitsDocument config = objectMapper.readValue(BET_LIMITS_JSON, BetLimitsDocument.class);

               	
			betLimitsRepository.save(config);
        }
    }
    
    
    
}