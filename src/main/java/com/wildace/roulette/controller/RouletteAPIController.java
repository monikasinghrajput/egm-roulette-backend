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
package com.wildace.roulette.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wildace.roulette.services.RouletteService;

@RestController
@RequestMapping("/api/v1/roulette")
public class RouletteAPIController {

	private final RouletteService rouletteService;

	public RouletteAPIController(RouletteService rouletteService) {
		this.rouletteService = rouletteService;
	}

	@GetMapping("/coins")
	public String sendConfigDataJson() {
		return """
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
	}
	
	@GetMapping("/payouts")
	public String sendPayoutsJson() {
		return """
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
	}

	@GetMapping("/limits")
	public String sendLimitsJson() {
		return """
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
	}
	
	@GetMapping("/hot-numbers")
	public String sendHotNumbersJson() {
		return """
				{
					28: 2, 
					29: 2, 
					11: 1, 
					7: 1, 
					2: 1
				}
				""";
	}

	@GetMapping("/cold-numbers")
	public String sendColdNumbersJson() {
		return """
				{
					25: 0, 
					16: 0, 
					36: 0, 
					23: 0, 
					26: 0
				}
				""";
	}

	@GetMapping("/statistics")
	public String sendStatisticsJson() {
		return """
				{
				    "0": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "1": {
				        "count": 2,
				        "percent": 16.0
				    },
				    "2": {
				        "count": 1,
				        "percent": 8.0
				    },
				    "3": {
				        "count": 1,
				        "percent": 8.0
				    },
				    "4": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "5": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "6": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "7": {
				        "count": 1,
				        "percent": 8.0
				    },
				    "8": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "9": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "10": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "11": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "12": {
				        "count": 1,
				        "percent": 8.0
				    },
				    "13": {
				        "count": 1,
				        "percent": 8.0
				    },
				    "14": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "15": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "16": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "17": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "18": {
				        "count": 1,
				        "percent": 8.0
				    },
				    "19": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "20": {
				        "count": 1,
				        "percent": 8.0
				    },
				    "21": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "22": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "23": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "24": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "25": {
				        "count": 2,
				        "percent": 16.0
				    },
				    "26": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "27": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "28": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "29": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "30": {
				        "count": 1,
				        "percent": 8.0
				    },
				    "31": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "32": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "33": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "34": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "35": {
				        "count": 0,
				        "percent": 0.0
				    },
				    "36": {
				        "count": 0,
				        "percent": 0.0
				    }

				}
			""";
	}

	@GetMapping("/groups")
	public String sendGroupsJson() {
		return """
				{
					"GroupFirstLine": 32,     
					"GroupSecondLine": 30,     
					"GroupThirdLine": 32,     
					"Group1to12": 25,     
					"Group13to24": 37,     
					"Group25to36": 32,     
					"GroupBlack": 40,     
					"GroupRed": 55,     
					"GroupOdd": 46,     
					"GroupEven": 49,     
					"Group1to18": 47,     
					"Group19to36": 48
				}
				""";
	}

	@GetMapping("/results")
	public String sendResultsJson() {
		return """
				[
				  	{
				  		"id": 1,
				  		"egmId": "WAS-1001",
				  		"uid": "53ce81df",
				  		"wallet": 500,
				  		"spinNumber": "20250519154251543",
				  		"betsList": [{"betIndex": 900, "betAmount": 100}],
				  		"wonBetsList": [{"betIndex": 900, "betAmount": 100, "winAmount": 200, "name": "Low"}],
				  		"betAmount": 100,
				  		"winAmount": 200,
				  		"oldCredit": 500,
				  		"newCredit": 600,
						"number": 7,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					},
					{
						"id": 2,
						"egmId": "WAS-1001",
						"uid": "53ce81df",
						"wallet": 600,
						"spinNumber": "20250519154251544",
				  		"betsList": [{"betIndex": 901, "betAmount": 100}],
				  		"wonBetsList": [{"betIndex": 901, "betAmount": 100, "winAmount": 200, "name": "Even"}],
						"betAmount": 100,
						"winAmount": 200,
						"oldCredit": 600,
						"newCredit": 700,
						"number": 12,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					},
					{
						"id": 3,
						"egmId": "WAS-1001",
						"uid": "53ce81df",
						"wallet": 700,
						"spinNumber": "20250519154251545",
				  		"betsList": [{"betIndex": 902, "betAmount": 100}],
				  		"wonBetsList": [{"betIndex": 902, "betAmount": 100, "winAmount": 200, "name": "Red"}],
						"betAmount": 100,
						"winAmount": 200,
						"oldCredit": 700,
						"newCredit": 800,
						"number": 18,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					},
					{
						"id": 4,
						"egmId": "WAS-1001",
						"uid": "53ce81df",
						"wallet": 800,
						"spinNumber": "20250519154251546",
				  		"betsList": [{"betIndex": 903, "betAmount": 100}],
				  		"wonBetsList": [{"betIndex": 903, "betAmount": 100, "winAmount": 200, "name": "Black"}],
						"betAmount": 100,
						"winAmount": 200,
						"oldCredit": 800,
						"newCredit": 900,
						"number": 20,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					},
					{
						"id": 5,
						"egmId": "WAS-1001",
						"uid": "53ce81df",
						"wallet": 900,
						"spinNumber": "20250519154251547",
				  		"betsList": [{"betIndex": 904, "betAmount": 100}],
				  		"wonBetsList": [{"betIndex": 904, "betAmount": 100, "winAmount": 200, "name": "Odd"}],
						"betAmount": 100,
						"winAmount": 200,
						"oldCredit": 900,
						"newCredit": 1000,
						"number": 25,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					},
					{
						"id": 6,
						"egmId": "WAS-1001",
						"uid": "53ce81df",
						"wallet": 1000,
						"spinNumber": "20250519154251548",
						"betsList": [{"betIndex": 905, "betAmount": 100}],
						"wonBetsList": [{"betIndex": 905, "betAmount": 100, "winAmount": 200, "name": "High"}],
						"betAmount": 100,
						"winAmount": 200,
						"oldCredit": 1000,
						"newCredit": 1100,
						"number": 30,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					},
					{
						"id": 7,
						"egmId": "WAS-1001",
						"uid": "53ce81df",
						"wallet": 1100,
						"spinNumber": "20250519154251549",
						"betsList": [{"betIndex": 800, "betAmount": 100}],
						"wonBetsList": [{"betIndex": 800, "betAmount": 100, "winAmount": 300, "name": "1st Dozen"}],
						"betAmount": 100,
						"winAmount": 300,
						"oldCredit": 1100,
						"newCredit": 1300,
						"number": 1,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					},
					{
						"id": 8,
						"egmId": "WAS-1001",
						"uid": "53ce81df",
						"wallet": 1300,
						"spinNumber": "20250519154251550",
						"betsList": [{"betIndex": 801, "betAmount": 100}],
						"wonBetsList": [{"betIndex": 801, "betAmount": 100, "winAmount": 300, "name": "2nd Dozen"}],
						"betAmount": 100,
						"winAmount": 300,
						"oldCredit": 1300,
						"newCredit": 1500,
						"number": 13,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					},
					{
						"id": 9,
						"egmId": "WAS-1001",
						"uid": "53ce81df",
						"wallet": 1500,
						"spinNumber": "20250519154251551",
						"betsList": [{"betIndex": 802, "betAmount": 100}],
						"wonBetsList": [{"betIndex": 802, "betAmount": 100, "winAmount": 300, "name": "3rd Dozen"}],
						"betAmount": 100,
						"winAmount": 300,
						"oldCredit": 1500,
						"newCredit": 1700,
						"number": 25,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					},
					{
						"id": 10,
						"egmId": "WAS-1001",
						"uid": "53ce81df",
						"wallet": 1700,
						"spinNumber": "20250519154251552",
						"betsList": [{"betIndex": 700, "betAmount": 100}],
						"wonBetsList": [{"betIndex": 700, "betAmount": 100, "winAmount": 300, "name": "3rd Column"}],
						"betAmount": 100,
						"winAmount": 300,
						"oldCredit": 1700,
						"newCredit": 1900,
						"number": 3,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					},
					{
						"id": 11,
						"egmId": "WAS-1001",
						"uid": "53ce81df",
						"wallet": 1900,
						"spinNumber": "20250519154251553",
						"betsList": [{"betIndex": 701, "betAmount": 100}],
						"wonBetsList": [{"betIndex": 701, "betAmount": 100, "winAmount": 300, "name": "2nd Column"}],
						"betAmount": 100,
						"winAmount": 300,
						"oldCredit": 1900,
						"newCredit": 2100,
						"number": 2,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					},
					{
						"id": 12,
						"egmId": "WAS-1001",
						"uid": "53ce81df",
						"wallet": 2100,
						"spinNumber": "20250519154251554",
						"betsList": [{"betIndex": 702, "betAmount": 100}],
						"wonBetsList": [{"betIndex": 702, "betAmount": 100, "winAmount": 300, "name": "1st Column"}],
						"betAmount": 100,
						"winAmount": 300,
						"oldCredit": 2100,
						"newCredit": 2300,
						"number": 1,
						"spinStart": "2025/05/19 15:42:51.534",
						"spinEnd": "2025/05/19 15:42:51.543"
					}						
				]
				""";
	}

	@PostMapping("/wheel-message")
	public String handleWheelMsg() {
		// Handle POST logic
		return """
				{
					data: "message received",
				}
				""";
	}
	
	@PostMapping("/spin-request")
	public String handleSpinRequest() {
		// Handle POST logic
		return """
				{
					data: "message received",
				}
				""";
	}
	

}
