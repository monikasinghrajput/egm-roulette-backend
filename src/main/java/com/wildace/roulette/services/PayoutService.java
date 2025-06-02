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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildace.roulette.domain.documents.PayoutDocument;
import com.wildace.roulette.repositories.PayoutRepository;

import java.util.List;
import java.util.Optional;
import jakarta.annotation.PostConstruct;

@Service
public class PayoutService {

    @Autowired
    private PayoutRepository payoutRepository;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PayoutService.class);
    public static final String PAYOUT_DOCUMENT_JSON = """
				{
					"id": 1,
					"payouts": [
						{
							"id": 1,
							"name": "Straight Up",
							"payout": 35
						},
						{
							"id": 2,
							"name": "Split",
							"payout": 17
						},
						{
							"id": 3,
							"name": "Street",
							"payout": 11
						},
						{
							"id": 4,
							"name": "Corner",
							"payout": 8
						},
						{
							"id": 5,
							"name": "Line",
							"payout": 5
						},
						{
							"id": 6,
							"name": "Column",
							"payout": 2
						},
						{
							"id": 7,
							"name": "Dozen",
							"payout": 2
						},
						{
							"id": 8,
							"name": "Outside",
							"payout": 1
						}
					]
				}
			""";

    /**
     * Create or update a PayoutDocument
     * 
     * @param payoutDocument The PayoutDocument to save
     * @return The saved PayoutDocument
     */
    public PayoutDocument savePayout(PayoutDocument payoutDocument) {
        return payoutRepository.save(payoutDocument);
    }

    /**
     * Retrieve all PayoutDocuments
     * 
     * @return List of all PayoutDocuments
     */
    public List<PayoutDocument> getAllPayouts() {
        return payoutRepository.findAll();
    }

    /**
     * Retrieve a PayoutDocument by ID
     * 
     * @param id The ID of the PayoutDocument
     * @return Optional containing the PayoutDocument if found, or empty if not found
     */
    public Optional<PayoutDocument> getPayoutById(String id) {
        return payoutRepository.findById(id);
    }

    /**
     * Delete a PayoutDocument by ID
     * 
     * @param id The ID of the PayoutDocument to delete
     */
    public void deletePayoutById(String id) {
        payoutRepository.deleteById(id);
    }

    /**
     * Update a PayoutDocument if it exists, or throw an exception if not found
     * 
     * @param id The ID of the PayoutDocument to update
     * @param updatedPayoutDocument The updated PayoutDocument data
     * @return The updated PayoutDocument
     * @throws RuntimeException if the PayoutDocument is not found
     */
    public PayoutDocument updatePayout(String id, PayoutDocument updatedPayoutDocument) {
        Optional<PayoutDocument> existingPayout = payoutRepository.findById(id);
        if (existingPayout.isPresent()) {
            updatedPayoutDocument.setId(id); // Ensure the same ID is used
            return payoutRepository.save(updatedPayoutDocument);
        } else {
            throw new RuntimeException("PayoutDocument with ID " + id + " not found");
        }
    }
    
    /**
	 * Check if a Payout exists in the document, and add it if not
	 * 
	 * @param documentId The ID of the PayoutDocument
	 * @param newPayout The new Payout to add
	 * @return The updated PayoutDocument
	 */
    public PayoutDocument checkAndAddPayout(String documentId, PayoutDocument.Payout newPayout) {
    	// Check if the document with the given ID exists
		Optional<PayoutDocument> optionalPayoutDocument = payoutRepository.findById(documentId);

		if (optionalPayoutDocument.isPresent()) {
			PayoutDocument payoutDocument = optionalPayoutDocument.get();
			boolean payoutExists = payoutDocument.getPayouts().stream()
					.anyMatch(payout -> payout.getId() == newPayout.getId());

			if (!payoutExists) {
				payoutDocument.getPayouts().add(newPayout);
				return payoutRepository.save(payoutDocument);
			} else {
				return payoutDocument; // Payout already exists, return the existing document
			}
		} else {
			// If the document doesn't exist, create a new one with the given payout
			PayoutDocument newDocument = new PayoutDocument();
			newDocument.setId(documentId);
			newDocument.setPayouts(List.of(newPayout));
			return payoutRepository.save(newDocument);
		}
	}
    
    @PostConstruct
	public void insertDefaultPayoutIfMissing() {
		// Check if any PayoutDocument exists; if not, insert default.
		if (payoutRepository.count() == 0) {
			log.info("No PayoutDocument found, inserting default payouts.");
	
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				PayoutDocument defaultPayout = objectMapper.readValue(PAYOUT_DOCUMENT_JSON, PayoutDocument.class);
				payoutRepository.save(defaultPayout);
				log.info("Default payouts inserted successfully.");
			} catch (Exception e) {
				log.error("Error inserting default payouts: ", e);
			}
		}
	}
}
