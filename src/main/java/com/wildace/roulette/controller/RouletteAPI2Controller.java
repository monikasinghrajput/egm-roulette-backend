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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wildace.roulette.domain.Stat;
import com.wildace.roulette.domain.api.SpinRequest;
import com.wildace.roulette.domain.api.SpinResponse;
import com.wildace.roulette.domain.documents.BetLimitsDocument;
import com.wildace.roulette.domain.documents.CoinDocument;
import com.wildace.roulette.domain.documents.PayoutDocument;
import com.wildace.roulette.domain.documents.SpinResult;
import com.wildace.roulette.services.BetLimitsService;
import com.wildace.roulette.services.CoinService;
import com.wildace.roulette.services.PayoutService;
import com.wildace.roulette.services.RouletteGameLogics;
import com.wildace.roulette.services.RouletteService;
import com.wildace.roulette.services.SpinResultService;
import com.wildace.roulette.services.TransactionService;
import com.wildace.roulette.services.UserService;

@RestController
@RequestMapping("/api/v2/roulette")
public class RouletteAPI2Controller {

	private final RouletteService rouletteService;
	private final CoinService coinService;
	private final PayoutService payoutService;
	private final BetLimitsService betLimitsService;
	private final SpinResultService spinResultService;
	private final UserService userService;
	private final TransactionService transactionService;

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RouletteAPI2Controller.class);
	
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final int STATUS_OK = 200;
	private static final int STATUS_BAD_REQUEST = 400;
	private static final int STATUS_SERVER_ERROR = 500;
	
	public RouletteAPI2Controller(
			RouletteService rouletteService, 
			CoinService coinService, 
			PayoutService payoutService, 
			BetLimitsService betLimitsService,
			SpinResultService spinResultService,
			UserService userService,
			TransactionService transactionService) {
		this.rouletteService = rouletteService;
		this.coinService = coinService;
		this.payoutService = payoutService;
		this.betLimitsService = betLimitsService;
		this.spinResultService = spinResultService;
		this.userService = userService;
		this.transactionService = transactionService;
		
	}

    public static String generatePrimaryKey() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return now.format(formatter);
    }
    
    private Map<Integer, Long> groupByWinNumberFrequency(List<SpinResult> spinResults) {
        // Use Stream API to group and count the occurrences of each WinNumber
        var spinResultsMap = spinResults.stream()
                .collect(Collectors.groupingBy(
                        SpinResult::getNumber, // Group by the `number` field
                        Collectors.counting()  // Count the occurrences
                ));
        
        // Any Missing WinNumber will be added with 0 count
        for (int i = 0; i <= RouletteGameLogics.MAX_POCKETS; i++) {
			spinResultsMap.putIfAbsent(i, 0L);
		}
        
        
        return spinResultsMap;
    }
    
    private Map<Integer, Long> groupByWinNumberFrequencyPercent(List<SpinResult> spinResults) {
    	// Use Stream API to group and count the occurrences of each WinNumber
    	var spinResultsMap = spinResults.stream()
    			.collect(Collectors.groupingBy(
    					SpinResult::getNumber, // Group by the `number` field
						Collectors.counting()  // Count the occurrences    					
    					));
    	
    	// Any Missing WinNumber will be added with 0 count
    	for (int i = 0; i <= RouletteGameLogics.MAX_POCKETS; i++) {
    		spinResultsMap.putIfAbsent(i, 0L);
    	}
    	
    	var spinResultsMapPercent = spinResultsMap.entrySet()
				.stream()
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						entry -> (entry.getValue() * 100) / spinResults.size(), // Calculate percentage
						(e1, e2) -> e1, // Merge function (not used here, but required for toMap)
						LinkedHashMap::new // Maintain insertion order
						));
    
    	return spinResultsMapPercent;
    }
    
    
	@GetMapping("/hot-numbers")
	public Map<Integer, Long> sendHotNumbersJson() {
		var spinResults = spinResultService.getAllSpinResults();

		// Group by WinNumber frequency
        Map<Integer, Long> winNumberFrequency = groupByWinNumberFrequency(spinResults);

        
     // Sort the map by value in descending order and take the top 5 entries
        var hotNumbers = winNumberFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .limit(5) // Apply limit here
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // Merge function (not used here, but required for toMap)
                        LinkedHashMap::new // Maintain insertion order
                ));
		
		// Return the top 5 hot numbers
		return hotNumbers;
	}

	@GetMapping("/cold-numbers")
	public Map<Integer, Long> sendColdNumbersJson() {
		var spinResults = spinResultService.getAllSpinResults();
		
		// Group by WinNumber frequency
		Map<Integer, Long> winNumberFrequency = groupByWinNumberFrequency(spinResults);

		
	 // Sort the map by value in ascending order and take the top 5 entries
		var coldNumbers = winNumberFrequency.entrySet()
				.stream()
				.sorted(Map.Entry.<Integer, Long>comparingByValue())
				.limit(5) // Apply limit here
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(e1, e2) -> e1, // Merge function (not used here, but required for toMap)
						LinkedHashMap::new // Maintain insertion order
				));
		
		// Return the top 5 cold numbers
		return coldNumbers;
	}

	@GetMapping("/statistics")
	public Map<Integer, Stat> sendStatisticsJson() {
		var spinResults = spinResultService.getAllSpinResults();
		
	    if (spinResults == null || spinResults.isEmpty()) {
	        var emptyStatistics = new LinkedHashMap<Integer, Stat>();
	        for (int i = 0; i <= RouletteGameLogics.MAX_POCKETS; i++) {
	            emptyStatistics.put(i, new Stat(0, 0.0));
	        }
	        return emptyStatistics;
	    }
	    
		Map<Integer, Long> winNumberFrequency = groupByWinNumberFrequency(spinResults);
		
		// Group by WinNumber frequency
		Map<Integer, Long> winNumberFrequencyPercent = groupByWinNumberFrequencyPercent(spinResults);
		
		
		//Map the list to Map<Integer, Stat>
		Map<Integer, Stat> statistics = winNumberFrequencyPercent.entrySet()
				.stream()
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						entry -> new Stat(winNumberFrequency.getOrDefault(entry.getKey(), 0L), entry.getValue()),
						(e1, e2) -> e1, // Merge function (not used here, but required for toMap)
						LinkedHashMap::new // Maintain insertion order
				));
		
		
		
		return statistics;
	}

    private Double calculatePercentage(Map<Integer, Long> winNumberFrequency, List<Integer> group, int totalSpins) {
        if (totalSpins == 0) {
            return 0.0; // Avoid division by zero
        }
        Long groupFrequency = winNumberFrequency.entrySet()
            .stream()
            .filter(entry -> group.contains(entry.getKey()))
            .map(Map.Entry::getValue)
            .reduce(0L, Long::sum);

        Double calculatedPercentage = (groupFrequency * 100.0) / totalSpins;
        calculatedPercentage = Math.round(calculatedPercentage * 100.0) / 100.0; // Round to 2 decimal places
        
        return calculatedPercentage;
    }

	@GetMapping("/groups")
	public Map<String, Double> sendGroupsJson() {
	    var spinResults = spinResultService.getAllSpinResults();
	    if (spinResults.isEmpty()) {
	    	Map<String, Double> emptyGroupPercentages = new LinkedHashMap<>();
	    	emptyGroupPercentages.put("GroupRed", 0.0);
	    	emptyGroupPercentages.put("GroupBlack", 0.0);
	    	emptyGroupPercentages.put("GroupOdd", 0.0);
	    	emptyGroupPercentages.put("GroupEven", 0.0);
	    	emptyGroupPercentages.put("Group1to18", 0.0);
	    	emptyGroupPercentages.put("Group19to36", 0.0);
	    	emptyGroupPercentages.put("GroupDozen1", 0.0);
	    	emptyGroupPercentages.put("GroupDozen2", 0.0);
	    	emptyGroupPercentages.put("GroupDozen3", 0.0);
	    	emptyGroupPercentages.put("GroupColumn1", 0.0);
	    	emptyGroupPercentages.put("GroupColumn2", 0.0);
	    	emptyGroupPercentages.put("GroupColumn3", 0.0);
	        return emptyGroupPercentages;
	    }

	    Map<Integer, Long> winNumberFrequency = groupByWinNumberFrequency(spinResults);

	    Map<String, Double> groupPercentages = new LinkedHashMap<>();
	    groupPercentages.put("GroupRed", calculatePercentage(winNumberFrequency, RouletteGameLogics.RED, spinResults.size()));
	    groupPercentages.put("GroupBlack", calculatePercentage(winNumberFrequency, RouletteGameLogics.BLACK, spinResults.size()));
	    groupPercentages.put("GroupOdd", calculatePercentage(winNumberFrequency, RouletteGameLogics.ODD, spinResults.size()));
	    groupPercentages.put("GroupEven", calculatePercentage(winNumberFrequency, RouletteGameLogics.EVEN, spinResults.size()));
	    groupPercentages.put("Group1to18", calculatePercentage(winNumberFrequency, RouletteGameLogics.LOW, spinResults.size()));
	    groupPercentages.put("Group19to36", calculatePercentage(winNumberFrequency, RouletteGameLogics.HIGH, spinResults.size()));
	    groupPercentages.put("GroupDozen1", calculatePercentage(winNumberFrequency, RouletteGameLogics.DOZEN1, spinResults.size()));
	    groupPercentages.put("GroupDozen2", calculatePercentage(winNumberFrequency, RouletteGameLogics.DOZEN2, spinResults.size()));
	    groupPercentages.put("GroupDozen3", calculatePercentage(winNumberFrequency, RouletteGameLogics.DOZEN3, spinResults.size()));
	    groupPercentages.put("GroupColumn1", calculatePercentage(winNumberFrequency, RouletteGameLogics.COLUMN1, spinResults.size()));
	    groupPercentages.put("GroupColumn2", calculatePercentage(winNumberFrequency, RouletteGameLogics.COLUMN2, spinResults.size()));
	    groupPercentages.put("GroupColumn3", calculatePercentage(winNumberFrequency, RouletteGameLogics.COLUMN3, spinResults.size()));

	    return groupPercentages;
	}


	//########################################################################################
	// SpinResult API
	//########################################################################################
	
    // Create or Update SpinResult
    @PostMapping("/results")
    public ResponseEntity<SpinResult> createOrUpdateSpinResult(@RequestBody SpinResult spinResult) {
        SpinResult savedSpinResult = spinResultService.saveSpinResult(spinResult);
        return new ResponseEntity<>(savedSpinResult, HttpStatus.CREATED);
    }
    
    // Get all SpinResults
    @GetMapping("/results")
    public ResponseEntity<List<SpinResult>> getAllSpinResults() {
        List<SpinResult> spinResults = spinResultService.getAllSpinResults();
        return new ResponseEntity<>(spinResults, HttpStatus.OK);
    }

    // Get a SpinResult by ID
    @GetMapping("/results/{id}")
    public ResponseEntity<SpinResult> getSpinResultById(@PathVariable Integer id) {
        Optional<SpinResult> spinResult = spinResultService.getSpinResultById(id);
        return spinResult.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get a SpinResult by UID
    @GetMapping("/results/uid/{uid}")
    public ResponseEntity<SpinResult> getSpinResultByUid(@PathVariable String uid) {
        Optional<SpinResult> spinResult = spinResultService.getSpinResultByUid(uid);
        return spinResult.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Delete a SpinResult by ID
    @DeleteMapping("/results/{id}")
    public ResponseEntity<Void> deleteSpinResultById(@PathVariable Integer id) {
        Optional<SpinResult> spinResult = spinResultService.getSpinResultById(id);
        if (spinResult.isPresent()) {
            spinResultService.deleteSpinResultById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update SpinResult
    @PutMapping("/results/{id}")
    public ResponseEntity<SpinResult> updateSpinResult(@PathVariable Integer id, @RequestBody SpinResult spinResult) {
        try {
            SpinResult updatedSpinResult = spinResultService.updateSpinResult(id, spinResult);
            return new ResponseEntity<>(updatedSpinResult, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

	//########################################################################################
	// BetLimits API
	//########################################################################################
	@PostMapping("/limits")
	public ResponseEntity<BetLimitsDocument> createOrUpdateBetLimits(@RequestBody BetLimitsDocument betLimitsDocument) {
		BetLimitsDocument savedDocument = betLimitsService.createBetLimits(betLimitsDocument);
		return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
	}
	
	@GetMapping("/limits")
	public ResponseEntity<List<BetLimitsDocument>> getAllBetLimits() {
		List<BetLimitsDocument> betLimits = betLimitsService.getAllBetLimits();
		return new ResponseEntity<>(betLimits, HttpStatus.OK);
	}
	@GetMapping("/limits/{id}")
	public ResponseEntity<BetLimitsDocument> getBetLimitsById(@PathVariable String id) {
		Optional<BetLimitsDocument> betLimits = betLimitsService.getBetLimitsById(id);
		if (betLimits.isPresent()) {
			return new ResponseEntity<>(betLimits.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/limits/{id}")
	public ResponseEntity<BetLimitsDocument> updateBetLimits(@PathVariable String id, @RequestBody BetLimitsDocument updatedBetLimits) {
		try {
			BetLimitsDocument savedDocument = betLimitsService.updateBetLimits(id, updatedBetLimits);
			return new ResponseEntity<>(savedDocument, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/limits/{id}")
	public ResponseEntity<Void> deleteBetLimits(@PathVariable String id) {
		betLimitsService.deleteBetLimitsById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	//########################################################################################
	// PayoutDocument API
	//########################################################################################
    @PostMapping("/payouts")
    public ResponseEntity<PayoutDocument> createOrUpdatePayout(@RequestBody PayoutDocument payoutDocument) {
        PayoutDocument savedPayout = payoutService.savePayout(payoutDocument);
        return new ResponseEntity<>(savedPayout, HttpStatus.CREATED);
    }
    
    @GetMapping("/payouts")
    public ResponseEntity<List<PayoutDocument>> getAllPayouts() {
		List<PayoutDocument> payouts = payoutService.getAllPayouts();
		return new ResponseEntity<>(payouts, HttpStatus.OK);
	}
    
    @GetMapping("/payouts/{id}")
	public ResponseEntity<PayoutDocument> getPayoutById(@PathVariable String id) {
		Optional<PayoutDocument> payout = payoutService.getPayoutById(id);
		if (payout.isPresent()) {
			return new ResponseEntity<>(payout.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/payouts/{id}")
	public ResponseEntity<PayoutDocument> updatePayout(@PathVariable String id, @RequestBody PayoutDocument updatedPayout) {
		try {
			PayoutDocument savedPayout = payoutService.updatePayout(id, updatedPayout);
			return new ResponseEntity<>(savedPayout, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/payouts/{id}")
	public ResponseEntity<Void> deletePayout(@PathVariable String id) {
		payoutService.deletePayoutById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/payouts/{id}/add-payout")
	public ResponseEntity<PayoutDocument> checkAndAddPayout(@PathVariable String id, @RequestBody PayoutDocument.Payout newPayout) {
		PayoutDocument updatedDocument = payoutService.checkAndAddPayout(id, newPayout);
		return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
	}
	
	
	//########################################################################################
	// CoinDocument API
	//########################################################################################

	// Create a new CoinDocument
    @PostMapping("/coins")
    public ResponseEntity<CoinDocument> createCoinDocument(@RequestBody CoinDocument coinDocument) {
        CoinDocument createdDocument = coinService.saveCoinDocument(coinDocument);
        return new ResponseEntity<>(createdDocument, HttpStatus.CREATED);
    }

    // Retrieve all CoinDocuments
    @GetMapping("/coins")
    public ResponseEntity<List<CoinDocument>> getAllCoinDocuments() {
        List<CoinDocument> documents = coinService.getAllCoinDocuments();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    // Retrieve a single CoinDocument by its ID
    @GetMapping("/coins/{id}")
    public ResponseEntity<CoinDocument> getCoinDocumentById(@PathVariable String id) {
        Optional<CoinDocument> document = coinService.getCoinDocumentById(id);
        if (document.isPresent()) {
            return new ResponseEntity<>(document.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing CoinDocument by its ID
    @PutMapping("/coins/{id}")
    public ResponseEntity<CoinDocument> updateCoinDocument(@PathVariable String id, @RequestBody CoinDocument coinDocument) {
        Optional<CoinDocument> existingDocument = coinService.getCoinDocumentById(id);
        if (existingDocument.isPresent()) {
            coinDocument.setId(id); // Ensure the ID remains the same
            CoinDocument updatedDocument = coinService.saveCoinDocument(coinDocument);
            return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a CoinDocument by its ID
    @DeleteMapping("/coins/{id}")
    public ResponseEntity<Void> deleteCoinDocumentById(@PathVariable String id) {
        Optional<CoinDocument> document = coinService.getCoinDocumentById(id);
        if (document.isPresent()) {
            coinService.deleteCoinDocumentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Check if a particular coin is present, if not add it
    @PostMapping("/coins/{id}/add-coin")
    public ResponseEntity<CoinDocument> checkAndAddCoin(@PathVariable String id, @RequestBody CoinDocument.Coin newCoin) {
        CoinDocument updatedDocument = coinService.checkAndAddCoin(id, newCoin);
        return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
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
    public SpinResponse processSpin(@RequestBody SpinRequest spinRequest) {
    	
        SpinResponse response = new SpinResponse();
        response.setSpinStart(now());

        // 1. Validate request
        if (spinRequest == null || spinRequest.getBetsList() == null || spinRequest.getBetsList().isEmpty()) {
            return errorResponse(response, "Invalid request: Bets list is empty", STATUS_BAD_REQUEST);
        }

        var uid = spinRequest.getUid();
        if (uid == null || uid.isEmpty()) {
            return errorResponse(response, "Invalid request: User not found", STATUS_BAD_REQUEST);
        }

        var user = userService.findOne(uid);
        if (user == null) {
            return errorResponse(response, "Invalid request: User does not exist", STATUS_BAD_REQUEST);
        }

        double betAmount = spinRequest.getBetsList().stream()
            .mapToDouble(SpinRequest.Bet::getBetAmount).sum();
        Double userWallet = user.getWallet();

        if (userWallet == null || userWallet <= 0 || userWallet < betAmount) {
            return errorResponse(response, "Invalid request: User wallet is empty or insufficient", STATUS_BAD_REQUEST);
        }

        // 2. Process spin
        var id = generatePrimaryKey();
        var spinNumber = rouletteService.generateSpinNumber();
        int spinResult = RouletteGameLogics.getWheelResult();

        List<SpinResponse.Bet> betsList = mapBetsToSpinResponse(spinRequest.getBetsList());
        List<SpinResponse.WonBet> wonBetsList = mapWonBetsToSpinResponse(spinRequest.getBetsList(), spinResult);

        double winAmount = wonBetsList.stream().mapToDouble(SpinResponse.WonBet::getWinAmount).sum();

        // 3. Update user wallet
        user.setWallet(userWallet + winAmount - betAmount);
        user.setUpdatedAt(now());
        var updatedUser = userService.updateOne(uid, user);

        if (updatedUser == null) {
            // Rollback
            user.setWallet(userWallet);
            userService.updateOne(uid, user);
            return errorResponse(response, "Error updating user wallet", STATUS_SERVER_ERROR);
        }

        // 4. Prepare response
        response.setId(id);
        response.setSpinNumber(spinNumber);
        response.setEgmId(spinRequest.getEgmId());
        response.setUid(uid);
        response.setBetsList(betsList);
        response.setBetAmount(betAmount);
        response.setWallet(userWallet);
        response.setNumber(spinResult);
        response.setWinAmount(winAmount);
        response.setOldCredit(userWallet);
        response.setNewCredit(updatedUser.getWallet());
        response.setWonBetsList(wonBetsList);
        response.setSpinEnd(now());
        response.setMsg("Spin completed successfully");
        response.setOk(1);
        response.setStatusCode(STATUS_OK);

        // 5. Save spin result (ideally in a transaction with wallet update)
        saveSpinResult(response, betsList, wonBetsList);

        log.info("Spin completed: SpinNumber={}, BetAmount={}, CurrentWallet={}, SpinResult={}, UserWallet={}, WinAmount={}",
                spinNumber, betAmount, userWallet, spinResult, updatedUser.getWallet(), winAmount);

        return response;
    }
	
    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    private SpinResponse errorResponse(SpinResponse response, String msg, int statusCode) {
        response.setOk(0);
        response.setMsg(msg);
        response.setStatusCode(statusCode);
        response.setSpinEnd(now());
        return response;
    }

    private List<SpinResponse.Bet> mapBetsToSpinResponse(List<SpinRequest.Bet> bets) {
        return bets.stream().map(bet -> {
            SpinResponse.Bet spinBet = new SpinResponse.Bet();
            spinBet.setBetIndex(bet.getBetIndex());
            spinBet.setBetAmount(bet.getBetAmount());
            return spinBet;
        }).collect(Collectors.toList());
    }

    private List<SpinResponse.WonBet> mapWonBetsToSpinResponse(List<SpinRequest.Bet> bets, int spinResult) {
        return bets.stream()
            .filter(bet -> RouletteGameLogics.isBetWon(bet.getBetIndex(), spinResult))
            .map(bet -> {
                SpinResponse.WonBet wonBet = new SpinResponse.WonBet();
                wonBet.setBetIndex(bet.getBetIndex());
                wonBet.setBetAmount(bet.getBetAmount());
                wonBet.setWinAmount(RouletteGameLogics.calculateWinAmount(bet.getBetIndex(), bet.getBetAmount(), spinResult));
                wonBet.setName(RouletteGameLogics.getBetName(bet.getBetIndex()));
                return wonBet;
            }).collect(Collectors.toList());
    }

    private void saveSpinResult(
            SpinResponse response,
            List<SpinResponse.Bet> betsList,
            List<SpinResponse.WonBet> wonBetsList
    ) {
        SpinResult spinResultObj = new SpinResult();
        spinResultObj.setId(response.getId());
        spinResultObj.setSpinNumber(response.getSpinNumber());
        spinResultObj.setEgmId(response.getEgmId());
        spinResultObj.setUid(response.getUid());
        spinResultObj.setBetsList(mapBetsToSpinResult(betsList));
        spinResultObj.setBetAmount(response.getBetAmount());
        spinResultObj.setNumber(response.getNumber());
        spinResultObj.setWallet(response.getWallet());
        spinResultObj.setWinAmount(response.getWinAmount());
        spinResultObj.setOldCredit(response.getOldCredit());
        spinResultObj.setNewCredit(response.getNewCredit());
        spinResultObj.setWonBetsList(mapWonBetsToSpinResult(wonBetsList));
        spinResultObj.setSpinStart(response.getSpinStart());
        spinResultObj.setSpinEnd(response.getSpinEnd());
        spinResultService.saveSpinResult(spinResultObj);
    }

    private List<SpinResult.Bet> mapBetsToSpinResult(List<SpinResponse.Bet> bets) {
        return bets.stream().map(bet -> {
            SpinResult.Bet spinBet = new SpinResult.Bet();
            spinBet.setBetIndex(bet.getBetIndex());
            spinBet.setBetAmount(bet.getBetAmount());
            return spinBet;
        }).collect(Collectors.toList());
    }

    private List<SpinResult.WonBet> mapWonBetsToSpinResult(List<SpinResponse.WonBet> wonBets) {
        return wonBets.stream().map(wonBet -> {
            SpinResult.WonBet spinWonBet = new SpinResult.WonBet();
            spinWonBet.setBetIndex(wonBet.getBetIndex());
            spinWonBet.setBetAmount(wonBet.getBetAmount());
            spinWonBet.setWinAmount(wonBet.getWinAmount());
            spinWonBet.setName(wonBet.getName());
            return spinWonBet;
        }).collect(Collectors.toList());
    }

}

