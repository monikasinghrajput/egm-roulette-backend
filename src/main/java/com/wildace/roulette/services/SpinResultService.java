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

import com.wildace.roulette.domain.documents.SpinResult;
import com.wildace.roulette.repositories.SpinResultRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SpinResultService {

    private final SpinResultRepository spinResultRepository;

    @Autowired
    public SpinResultService(SpinResultRepository spinResultRepository) {
        this.spinResultRepository = spinResultRepository;
    }

    // Create or Update a SpinResult
    public SpinResult saveSpinResult(SpinResult spinResult) {
        return spinResultRepository.save(spinResult);
    }

    // Retrieve all SpinResults
    public List<SpinResult> getAllSpinResults() {
        return spinResultRepository.findAll();
    }

    // Retrieve a SpinResult by ID
    public Optional<SpinResult> getSpinResultById(Integer id) {
        return spinResultRepository.findById(id);
    }

    // Retrieve a SpinResult by UID
    public Optional<SpinResult> getSpinResultByUid(String uid) {
        return Optional.ofNullable(spinResultRepository.findByUid(uid));
    }

    // Delete a SpinResult by ID
    public void deleteSpinResultById(Integer id) {
        spinResultRepository.deleteById(id);
    }

    // Update a SpinResult (if it exists)
    public SpinResult updateSpinResult(Integer id, SpinResult updatedSpinResult) {
        Optional<SpinResult> existingSpinResultOpt = spinResultRepository.findById(id);
        if (existingSpinResultOpt.isPresent()) {
            SpinResult existingSpinResult = existingSpinResultOpt.get();
            // Update fields
            existingSpinResult.setEgmId(updatedSpinResult.getEgmId());
            existingSpinResult.setUid(updatedSpinResult.getUid());
            existingSpinResult.setWallet(updatedSpinResult.getWallet());
            existingSpinResult.setSpinNumber(updatedSpinResult.getSpinNumber());
            existingSpinResult.setBetsList(updatedSpinResult.getBetsList());
            existingSpinResult.setWonBetsList(updatedSpinResult.getWonBetsList());
            existingSpinResult.setBetAmount(updatedSpinResult.getBetAmount());
            existingSpinResult.setWinAmount(updatedSpinResult.getWinAmount());
            existingSpinResult.setOldCredit(updatedSpinResult.getOldCredit());
            existingSpinResult.setNewCredit(updatedSpinResult.getNewCredit());
            existingSpinResult.setNumber(updatedSpinResult.getNumber());
            existingSpinResult.setSpinStart(updatedSpinResult.getSpinStart());
            existingSpinResult.setSpinEnd(updatedSpinResult.getSpinEnd());
            return spinResultRepository.save(existingSpinResult);
        } else {
            throw new IllegalArgumentException("SpinResult with ID " + id + " does not exist.");
        }
    }
}
