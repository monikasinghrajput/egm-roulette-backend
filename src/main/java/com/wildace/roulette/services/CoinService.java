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
import java.util.Optional;
import jakarta.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildace.roulette.domain.documents.CoinDocument;
import com.wildace.roulette.repositories.CoinRepository;

@Service
public class CoinService {

	private final CoinRepository coinRepository;
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CoinService.class);
    
    public static final String COIN_DOCUMENT_JSON = """
				{
					"id": 1,
					"coins": [
						{
							"id": 1,
							"denom": 10,
							"color": "red",
							"currency": "INR"
						},
						{
							"id": 2,
							"denom": 50,
							"color": "black",
							"currency": "INR"
						},
						{
							"id": 3,
							"denom": 100,
							"color": "green",
							"currency": "INR"
						},
						{
							"id": 4,
							"denom": 500,
							"color": "blue",
							"currency": "INR"
						},
						{
							"id": 5,
							"denom": 1000,
							"color": "yellow",
							"currency": "INR"
						}
					]
				}
			""";

    @Autowired
    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    // Save a coin document
    public CoinDocument saveCoinDocument(CoinDocument coinDocument) {
        return coinRepository.save(coinDocument);
    }

    // Retrieve all coin documents
    public List<CoinDocument> getAllCoinDocuments() {
        return coinRepository.findAll();
    }

    // Retrieve a coin document by its ID
    public Optional<CoinDocument> getCoinDocumentById(String id) {
        return coinRepository.findById(id);
    }

    // Update a coin document
    public CoinDocument updateCoinDocument(String id, CoinDocument updatedCoinDocument) {
		return coinRepository.findById(id)
				.map(coinDocument -> {
					coinDocument.setCoins(updatedCoinDocument.getCoins());
					return coinRepository.save(coinDocument);
				})
				.orElseThrow(() -> new RuntimeException("Coin document not found"));
	}
    
    // Delete a coin document by its ID
    public void deleteCoinDocumentById(String id) {
        coinRepository.deleteById(id);
    }

    // Check if a particular coin is present, if not add it
    public CoinDocument checkAndAddCoin(String documentId, CoinDocument.Coin newCoin) {
        Optional<CoinDocument> optionalCoinDocument = coinRepository.findById(documentId);

        if (optionalCoinDocument.isPresent()) {
            CoinDocument coinDocument = optionalCoinDocument.get();
            boolean coinExists = coinDocument.getCoins().stream()
                    .anyMatch(coin -> coin.getId() == newCoin.getId());

            if (!coinExists) {
                coinDocument.getCoins().add(newCoin);
                return coinRepository.save(coinDocument);
            } else {
                return coinDocument; // Coin already exists, return the existing document
            }
        } else {
            // If the document doesn't exist, create a new one with the given coin
            CoinDocument newDocument = new CoinDocument();
            newDocument.setId(documentId);
            newDocument.setCoins(List.of(newCoin));
            return coinRepository.save(newDocument);
        }
    }
    
    @PostConstruct
    public void insertDefaultCoinsIfMissing() {
		// Example: Check if any CoinDocument exists; if not, insert default.
		if (coinRepository.count() == 0) {
			log.info("No CoinDocument found, inserting default coins.");
			
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				CoinDocument defaultCoinDocument = objectMapper.readValue(COIN_DOCUMENT_JSON, CoinDocument.class);
				coinRepository.save(defaultCoinDocument);
			} catch (Exception e) {
				log.error("Error inserting default coins: ", e);
			}
			
			
		}
	}
}
