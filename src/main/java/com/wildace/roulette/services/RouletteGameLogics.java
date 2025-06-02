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

import java.util.*;


// RouletteGameLogics.java
// This class contains the logic for the roulette game, including the groups of numbers, the wheel result, and the computation of group data based on the history of spins.	
// It also includes the mapping of bet types to their respective payouts and the groups they belong to.
// It is used to simulate the roulette game and calculate the results based on the bets placed by the players.

public class RouletteGameLogics {


	//define a constant for max number 
	public static final int MAX_POCKETS = 36;
	
    // Groups
    public static final List<Integer> LOW = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
    public static final List<Integer> EVEN = Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36);
    public static final List<Integer> BLACK = Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35);
    public static final List<Integer> RED = Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);
    public static final List<Integer> ODD = Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35);
    public static final List<Integer> HIGH = Arrays.asList(19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);

    // Columns
    public static final List<Integer> COLUMN3 = Arrays.asList(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36);
    public static final List<Integer> COLUMN2 = Arrays.asList(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35);
    public static final List<Integer> COLUMN1 = Arrays.asList(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34);

    // Dozens
    public static final List<Integer> DOZEN1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    public static final List<Integer> DOZEN2 = Arrays.asList(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    public static final List<Integer> DOZEN3 = Arrays.asList(25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);

    
  
    // Simulate a spin of the roulette wheel
    public static int getWheelResult() {
        Random random = new Random();
        return random.nextInt(37); // Return a random number between 0 and 36
    }

    public Group computeGroupData(List<Map.Entry<Integer, Integer>> history) {
    	// Initialize the group data
        int size = history.stream().mapToInt(Map.Entry::getValue).sum();
        // If size is 0, set it to 1 to avoid division by zero
        if (size == 0) size = 1;

        // Compute the percentage of each group
        int groupLow = computePercentage(history, LOW, size);
        int groupHigh = computePercentage(history, HIGH, size);
        
        int groupEven = computePercentage(history, EVEN, size);
        int groupOdd = computePercentage(history, ODD, size);
        
        int groupBlack = computePercentage(history, BLACK, size);
        int groupRed = computePercentage(history, RED, size);

        int groupColumn3 = computePercentage(history, COLUMN3, size);
        int groupColumn2 = computePercentage(history, COLUMN2, size);
        int groupColumn1 = computePercentage(history, COLUMN1, size);

        int groupDozen1 = computePercentage(history, DOZEN1, size);
        int groupDozen2 = computePercentage(history, DOZEN2, size);
        int groupDozen3 = computePercentage(history, DOZEN3, size);

        return new Group(
            groupLow,
            groupEven,
            groupBlack,
            groupRed,
            groupOdd,
            groupHigh,
            groupColumn1,
            groupColumn2,
            groupColumn3,
            groupDozen1,
            groupDozen2,
            groupDozen3
        );
    }

    private int computePercentage(List<Map.Entry<Integer, Integer>> history, List<Integer> group, int size) {
        return history.stream()
            .filter(entry -> group.contains(entry.getKey()))
            .mapToInt(Map.Entry::getValue)
            .sum() * 100 / size;
    }

    public static final Map<Integer, List<Integer>> BET_INDEX_MAP = new HashMap<>() {{
        put(100, Arrays.asList(0));
        put(101, Arrays.asList(1));//orpheline
        put(102, Arrays.asList(2));
        put(103, Arrays.asList(3));
        put(104, Arrays.asList(4));
        put(105, Arrays.asList(5));
        put(106, Arrays.asList(6));
        put(107, Arrays.asList(7));
        put(108, Arrays.asList(8));
        put(109, Arrays.asList(9));
        put(110, Arrays.asList(10));
        put(111, Arrays.asList(11));
        put(112, Arrays.asList(12));
        put(113, Arrays.asList(13));
        put(114, Arrays.asList(14));
        put(115, Arrays.asList(15));
        put(116, Arrays.asList(16));
        put(117, Arrays.asList(17));
        put(118, Arrays.asList(18));
        put(119, Arrays.asList(19));
        put(120, Arrays.asList(20));
        put(121, Arrays.asList(21));
        put(122, Arrays.asList(22));
        put(123, Arrays.asList(23));
        put(124, Arrays.asList(24));
        put(125, Arrays.asList(25));
        put(126, Arrays.asList(26));//Zero
        put(127, Arrays.asList(27));
        put(128, Arrays.asList(28));
        put(129, Arrays.asList(29));
        put(130, Arrays.asList(30));
        put(131, Arrays.asList(31));
        put(132, Arrays.asList(32));
        put(133, Arrays.asList(33));
        put(134, Arrays.asList(34));
        put(135, Arrays.asList(35));
        put(136, Arrays.asList(36));
        put(137, Arrays.asList(37));

        // Splits
        put(200, Arrays.asList(0, 3));//Zero
        put(201, Arrays.asList(3, 6));
        put(202, Arrays.asList(6, 9));//orpheline
        put(203, Arrays.asList(9, 12));
        put(204, Arrays.asList(12, 15));//Voisins/Zero
        put(205, Arrays.asList(15, 18));
        put(206, Arrays.asList(18, 21));//Voisins
        put(207, Arrays.asList(21, 24));
        put(208, Arrays.asList(24, 27));
        put(209, Arrays.asList(27, 30));//Tiers
        put(210, Arrays.asList(30, 33));
        put(211, Arrays.asList(33, 36));//Tiers

        put(212, Arrays.asList(2, 3));
        put(213, Arrays.asList(5, 6));
        put(214, Arrays.asList(8, 9));
        put(215, Arrays.asList(11, 12));
        put(216, Arrays.asList(14, 15));
        put(217, Arrays.asList(17, 18));
        put(218, Arrays.asList(20, 21));
        put(219, Arrays.asList(23, 24));//Tiers
        put(220, Arrays.asList(26, 27));
        put(221, Arrays.asList(29, 30));
        put(222, Arrays.asList(32, 33));
        put(223, Arrays.asList(35, 36));

        put(224, Arrays.asList(0, 2));
        put(225, Arrays.asList(2, 5));
        put(226, Arrays.asList(5, 8));//Tires
        put(227, Arrays.asList(8, 11));
        put(228, Arrays.asList(11, 14));
        put(229, Arrays.asList(14, 17));//orpheline
        put(230, Arrays.asList(17, 20));//orpheline
        put(231, Arrays.asList(20, 23));
        put(232, Arrays.asList(23, 26));
        put(233, Arrays.asList(26, 29));
        put(234, Arrays.asList(29, 32));
        put(235, Arrays.asList(32, 35));//Voisins/Zero

        put(236, Arrays.asList(1, 2));
        put(237, Arrays.asList(4, 5));
        put(238, Arrays.asList(7, 8));
        put(239, Arrays.asList(10, 11));//Tiers
        put(240, Arrays.asList(13, 14));
        put(241, Arrays.asList(16, 17));
        put(242, Arrays.asList(19, 20));
        put(243, Arrays.asList(22, 23));
        put(244, Arrays.asList(25, 26));
        put(245, Arrays.asList(28, 29));
        put(246, Arrays.asList(31, 32));
        put(247, Arrays.asList(34, 35));

        put(248, Arrays.asList(0, 1));
        put(249, Arrays.asList(1, 4));
        put(250, Arrays.asList(4, 7));//Voisins
        put(251, Arrays.asList(7, 10));
        put(252, Arrays.asList(10, 13));
        put(253, Arrays.asList(13, 16));//Tiers
        put(254, Arrays.asList(16, 19));
        put(255, Arrays.asList(19, 22));//Voisins
        put(256, Arrays.asList(22, 25));
        put(257, Arrays.asList(25, 28));
        put(258, Arrays.asList(28, 31));
        put(259, Arrays.asList(31, 34));//orpheline

        put(290, Arrays.asList(0, 37));
        put(291, Arrays.asList(3, 37));
        put(292, Arrays.asList(1, 0));

        // Trio
        put(300, Arrays.asList(0, 2, 3)); // Voisins
        put(301, Arrays.asList(0, 1, 2)); // 

        // Street
        put(500, Arrays.asList(1, 2, 3));
        put(501, Arrays.asList(4, 5, 6));
        put(502, Arrays.asList(7, 8, 9));
        put(503, Arrays.asList(10, 11, 12));
        put(504, Arrays.asList(13, 14, 15));
        put(505, Arrays.asList(16, 17, 18));
        put(506, Arrays.asList(19, 20, 21));
        put(507, Arrays.asList(22, 23, 24));
        put(508, Arrays.asList(25, 26, 27));
        put(509, Arrays.asList(28, 29, 30));
        put(510, Arrays.asList(31, 32, 33));
        put(511, Arrays.asList(34, 35, 36));

        put(590, Arrays.asList(0, 37, 2)); // American
        put(591, Arrays.asList(0, 1, 2)); // American
        put(592, Arrays.asList(37, 2, 3)); // American

        // Corners
        put(400, Arrays.asList(2, 3, 5, 6));
        put(401, Arrays.asList(5, 6, 8, 9));
        put(402, Arrays.asList(8, 9, 11, 12));
        put(403, Arrays.asList(11, 12, 14, 15));
        put(404, Arrays.asList(14, 15, 17, 18));
        put(405, Arrays.asList(17, 18, 20, 21));
        put(406, Arrays.asList(20, 21, 23, 24));
        put(407, Arrays.asList(23, 24, 26, 27));
        put(408, Arrays.asList(26, 27, 29, 30));
        put(409, Arrays.asList(29, 30, 32, 33));
        put(410, Arrays.asList(32, 33, 35, 36));

        put(411, Arrays.asList(1, 2, 4, 5));
        put(412, Arrays.asList(4, 5, 7, 8));
        put(413, Arrays.asList(7, 8, 10, 11));
        put(414, Arrays.asList(10, 11, 13, 14));
        put(415, Arrays.asList(13, 14, 16, 17));
        put(416, Arrays.asList(16, 17, 19, 20));
        put(417, Arrays.asList(19, 20, 22, 23));
        put(418, Arrays.asList(22, 23, 25, 26));
        put(419, Arrays.asList(25, 26, 28, 29));//Voisins
        put(420, Arrays.asList(28, 29, 31, 32));
        put(421, Arrays.asList(31, 32, 34, 35));
        put(422, Arrays.asList(0, 1, 2, 3));

        // Six Line
        put(600, Arrays.asList(1, 2, 3, 4, 5, 6));
        put(601, Arrays.asList(4, 5, 6, 7, 8, 9));
        put(602, Arrays.asList(7, 8, 9, 10, 11, 12));
        put(603, Arrays.asList(10, 11, 12, 13, 14, 15));
        put(604, Arrays.asList(13, 14, 15, 16, 17, 18));
        put(605, Arrays.asList(16, 17, 18, 19, 20, 21));
        put(606, Arrays.asList(19, 20, 21, 22, 23, 24));
        put(607, Arrays.asList(22, 23, 24, 25, 26, 27));
        put(608, Arrays.asList(25, 26, 27, 28, 29, 30));
        put(609, Arrays.asList(28, 29, 30, 31, 32, 33));
        put(610, Arrays.asList(31, 32, 33, 34, 35, 36));

        put(690, Arrays.asList(0, 37, 1, 2, 3));

        // Columns
        put(700, Arrays.asList(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36)); // 3rd
        put(701, Arrays.asList(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35)); // 2nd
        put(702, Arrays.asList(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34)); // 1st

        // Dozens
        put(800, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)); // 1st
        put(801, Arrays.asList(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24)); // 2nd
        put(802, Arrays.asList(25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36)); // 3rd

        // Outside Bets
        put(900, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)); // Low
        put(901, Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36)); // Even
        put(902, Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36)); // Red
        put(903, Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35)); // Black
        put(904, Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35)); // Odd
        put(905, Arrays.asList(19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36)); // High
    }};

    public static final Map<Integer, Integer> BET_PAYOUT = new HashMap<>() {{
        put(100, 35);
        put(101, 35);
        put(102, 35);
        put(103, 35);
        put(104, 35);
        put(105, 35);
        put(106, 35);
        put(107, 35);
        put(108, 35);
        put(109, 35);
        put(110, 35);
        put(111, 35);
        put(112, 35);
        put(113, 35);
        put(114, 35);
        put(115, 35);
        put(116, 35);
        put(117, 35);
        put(118, 35);
        put(119, 35);
        put(120, 35);
        put(121, 35);
        put(122, 35);
        put(123, 35);
        put(124, 35);
        put(125, 35);
        put(126, 35);
        put(127, 35);
        put(128, 35);
        put(129, 35);
        put(130, 35);
        put(131, 35);
        put(132, 35);
        put(133, 35);
        put(134, 35);
        put(135, 35);
        put(136, 35);
        put(137, 35);

        // Splits
        put(200, 17);
        put(201, 17);
        put(202, 17);
        put(203, 17);
        put(204, 17);
        put(205, 17);
        put(206, 17);
        put(207, 17);
        put(208, 17);
        put(209, 17);
        put(210, 17);
        put(211, 17);

        put(212, 17);
        put(213, 17);
        put(214, 17);
        put(215, 17);
        put(216, 17);
        put(217, 17);
        put(218, 17);
        put(219, 17);
        put(220, 17);
        put(221, 17);
        put(222, 17);
        put(223, 17);

        put(224, 17);
        put(225, 17);
        put(226, 17);
        put(227, 17);
        put(228, 17);
        put(229, 17);
        put(230, 17);
        put(231, 17);
        put(232, 17);
        put(233, 17);
        put(234, 17);
        put(235, 17);

        put(236, 17);
        put(237, 17);
        put(238, 17);
        put(239, 17);
        put(240, 17);
        put(241, 17);
        put(242, 17);
        put(243, 17);
        put(244, 17);
        put(245, 17);
        put(246, 17);
        put(247, 17);

        put(248, 17);
        put(249, 17);
        put(250, 17);
        put(251, 17);
        put(252, 17);
        put(253, 17);
        put(254, 17);
        put(255, 17);
        put(256, 17);
        put(257, 17);
        put(258, 17);
        put(259, 17);

        put(290, 17);
        put(291, 17);
        put(292, 17);

        // Trio
        put(300, 11); // American
        put(301, 11); // American

        // Street
        put(500, 11);
        put(501, 11);
        put(502, 11);
        put(503, 11);
        put(504, 11);
        put(505, 11);
        put(506, 11);
        put(507, 11);
        put(508, 11);
        put(509, 11);
        put(510, 11);
        put(511, 11);

        put(590, 11); // American
        put(591, 11); // American
        put(592, 11); // American

        // Corners
        put(400, 8);
        put(401, 8);
        put(402, 8);
        put(403, 8);
        put(404, 8);
        put(405, 8);
        put(406, 8);
        put(407, 8);
        put(408, 8);
        put(409, 8);
        put(410, 8);

        put(411, 8);
        put(412, 8);
        put(413, 8);
        put(414, 8);
        put(415, 8);
        put(416, 8);
        put(417, 8);
        put(418, 8);
        put(419, 8);
        put(420, 8);
        put(421, 8);
        put(422, 8);

        // Six Line
        put(600, 5);
        put(601, 5);
        put(602, 5);
        put(603, 5);
        put(604, 5);
        put(605, 5);
        put(606, 5);
        put(607, 5);
        put(608, 5);
        put(609, 5);
        put(610, 5);

        put(690, 5);

        // Columns
        put(700, 2); // 3rd
        put(701, 2); // 2nd
        put(702, 2); // 1st

        // Dozens
        put(800, 2); // 1st
        put(801, 2); // 2nd
        put(802, 2); // 3rd

        // Outside Bets
        put(900, 1);
        put(901, 1);
        put(902, 1);
        put(903, 1);
        put(904, 1);
        put(905, 1); // High
    }};

    public static class Group {
        public int group1to18;
        public int groupEven;
        public int groupBlack;
        public int groupRed;
        public int groupOdd;
        public int group19to36;
        public int groupFirstLine;
        public int groupSecondLine;
        public int groupThirdLine;
        public int group1to12;
        public int group13to24;
        public int group25to36;

        public Group(int group1to18, int groupEven, int groupBlack, int groupRed, int groupOdd, int group19to36,
                     int groupFirstLine, int groupSecondLine, int groupThirdLine, int group1to12, int group13to24, int group25to36) {
            this.group1to18 = group1to18;
            this.groupEven = groupEven;
            this.groupBlack = groupBlack;
            this.groupRed = groupRed;
            this.groupOdd = groupOdd;
            this.group19to36 = group19to36;
            this.groupFirstLine = groupFirstLine;
            this.groupSecondLine = groupSecondLine;
            this.groupThirdLine = groupThirdLine;
            this.group1to12 = group1to12;
            this.group13to24 = group13to24;
            this.group25to36 = group25to36;
        }
    }

	public static boolean isBetWon(int betIndex, int spinResult) {
		// Check if the bet index is valid
		if (!BET_INDEX_MAP.containsKey(betIndex)) {
			return false; // Invalid bet index
		}
		// Get the list of numbers associated with the bet index
		List<Integer> betNumbers = BET_INDEX_MAP.get(betIndex);
		
		// Check if the spin result is in the list of numbers for the bet index
		if (betNumbers.contains(spinResult)) {
			return true; // Bet won
		}
		return false;
	}

	public static double calculateWinAmount(int betIndex, double betAmount, int spinResult) {
		// Check if the bet index is valid
		if (!BET_PAYOUT.containsKey(betIndex)) {
			return 0; // Invalid bet index
		}
		// Check if the bet is won
		if (isBetWon(betIndex, spinResult)) {
			// Calculate the win amount based on the bet amount and the payout for the bet index
			return betAmount * (BET_PAYOUT.get(betIndex) + 1); // +1 to include the original bet amount in the win
		}
		return 0;
	}

	public static String getBetName(int betIndex) {
	    // Check if the bet index is valid
	    if (BET_INDEX_MAP.containsKey(betIndex)) {
	        switch (betIndex) {
	            // Straight Up
	            case 100: case 101: case 102: case 103: case 104: case 105: case 106: case 107:
	            case 108: case 109: case 110: case 111: case 112: case 113: case 114: case 115:
	            case 116: case 117: case 118: case 119: case 120: case 121: case 122: case 123:
	            case 124: case 125: case 126: case 127: case 128: case 129: case 130: case 131:
	            case 132: case 133: case 134: case 135: case 136: case 137:
	                return "Straight Up";
	            // Split
	            case 200: case 201: case 202: case 203: case 204: case 205: case 206:
	            case 207: case 208: case 209: case 210: case 211:
	                return "Split";
	            // Trio
	            case 300: case 301:
	                return "Trio";
	            // Corner
	            case 400: case 401: case 402: case 403: case 404: case 405: case 406: case 407: case 408:
	            case 409: case 410: case 411: case 412: case 413: case 414: case 415: case 416: case 417:
	            case 418: case 419: case 420: case 421: case 422:
	                return "Corner";
	            // Street
	            case 500:
	            	return "Street 1";
	            case 501:
	            	return "Street 2";
	            case 502:
	            	return "Street 3";
	            case 503:
	            	return "Street 4";
	            case 504:
	            	return "Street 5";
	            case 505:
	            	return "Street 6";
	            case 506:
	            	return "Street 7";
	            case 507:
	            	return "Street 8";
	            case 508:
	            	return "Street 9";
	            case 509:
	            	return "Street 10";
	            case 510:
	            	return "Street 11";
	            case 511:
	            	return "Street 12";
	            	
	            // Six Line
	            case 600: 
	            	return "Six Line 1";
	            case 601: 
	            	return "Six Line 2";
	            case 602: 
	            	return "Six Line 3";
	            case 603: 
	            	return "Six Line 4";
	            case 604: 
	            	return "Six Line 5";
	            case 605: 
	            	return "Six Line 6";
	            case 606:
	            	return "Six Line 7";
	            case 607: 
	            	return "Six Line 8";
	            case 608: 
	            	return "Six Line 9";
	            case 609: 
	            	return "Six Line 10";
	            case 610:
	            	return "Six Line 11";
	            	
	            	
	            // Column
	            case 700: 
	            	return "Column 3";
	            case 701:
	            	return "Column 2";
	            case 702:
	                return "Column 1";
	            // Dozen
	            case 800: 
	            	return "Dozen 1";
	            case 801:
	            	return "Dozen 2";
	            case 802:
	                return "Dozen 3";
	            // Others
	            case 900:
	                return "Low";
	            case 901:
	                return "Even";
	            case 902:
	                return "Red";
	            case 903:
	                return "Black";
	            case 904:
	                return "Odd";
	            case 905:
	                return "High";
	            default:
	                return "Unknown Bet Type";
	        }
	    }
	    return "Invalid Bet Index";
	}
}
