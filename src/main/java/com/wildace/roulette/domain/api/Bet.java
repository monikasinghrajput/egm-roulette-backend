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
package com.wildace.roulette.domain.api;

import org.springframework.stereotype.Component;

@Component
public class Bet {

    private Integer betIndex;

    private Integer betAmount;

    // Default constructor
    public Bet() {}

    // Parameterized constructor
    public Bet(Integer betIndex, Integer betAmount) {
        this.betIndex = betIndex;
        this.betAmount = betAmount;
    }

    // Getter and Setter for betIndex
    public Integer getBetIndex() {
        return betIndex;
    }

    public void setBetIndex(Integer betIndex) {
        this.betIndex = betIndex;
    }

    // Getter and Setter for betAmount
    public Integer getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(Integer betAmount) {
        this.betAmount = betAmount;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "betIndex=" + betIndex +
                ", betAmount=" + betAmount +
                '}';
    }
}