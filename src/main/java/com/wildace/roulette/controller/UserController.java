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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wildace.roulette.domain.api.UserDepositRequest;
import com.wildace.roulette.domain.documents.Transaction;
import com.wildace.roulette.domain.documents.User;
import com.wildace.roulette.services.TransactionService;
import com.wildace.roulette.services.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	private final UserService userService;
	private final TransactionService transactionService;

	@Autowired
	public UserController(UserService userService, TransactionService transactionService) {
		this.userService = userService;
		this.transactionService = transactionService;
	}

    public static String generatePrimaryKey() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return now.format(formatter);
    }
    
	@GetMapping(path = "/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/users/{uid}")
	public User getUserByUid(@PathVariable String uid) {
		return userService.findOne(uid);
	}

	// Get User by UID passed as a query parameter - For Backward compatibility
	@GetMapping("/user")
	public User getUserByUidQuery(@RequestParam String uid) {
		return userService.findOne(uid);
	}

	
	
	@PostMapping("/deposit")
	public User updateUserByUidDeposit(@RequestBody UserDepositRequest request) {
		String uid = request.getUid();
		String egmId = request.getEgmId();
		int credit = request.getCredit();
		String transBy = request.getTransBy();
		
		// Create a new transaction
		Transaction transaction = new Transaction();
		transaction.setEgmId(egmId);
		transaction.setUid(uid);
		transaction.setTransId(generatePrimaryKey());
		transaction.setTransType("Deposit");
		transaction.setTransBy(transBy); // Assuming transBy is the user who made the transaction		
		transaction.setDepositAmount((double) credit);
		transaction.setWithdrawAmount(0.0);
		transaction.setPrevCredit((double) credit);
		transaction.setThenCredit(0.0);
		transaction.setTransStartTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		
		// Save the transaction to the database
		transactionService.createTransaction(transaction);

		User user = userService.findOne(uid);
		user.setWallet(user.getWallet() + credit);
		user.setIsPlaying(true);
		user.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return userService.updateOne(uid, user);
	}

	@PostMapping("/withdraw")
	public User updateUserByUidWithdraw(@RequestBody UserDepositRequest request) {
		String uid = request.getUid();
		String egmId = request.getEgmId();
		int credit = request.getCredit();
		String transBy = request.getTransBy();
		
		// Create a new transaction
		Transaction transaction = new Transaction();
		transaction.setEgmId(egmId);
		transaction.setUid(uid);
		transaction.setTransId(generatePrimaryKey());
		transaction.setTransType("Withdraw");
		transaction.setTransBy(transBy); // Assuming transBy is the user who made the transaction		
		transaction.setDepositAmount(0.0);
		transaction.setWithdrawAmount((double) credit);
		transaction.setPrevCredit(0.0);
		transaction.setThenCredit((double) credit);
		transaction.setTransStartTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				
		// Save the transaction to the database
		transactionService.createTransaction(transaction);

		User user = userService.findOne(uid);
		user.setWallet(user.getWallet() - credit);
		user.setIsPlaying(false);
		user.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return userService.updateOne(uid, user);
	}

	
	
	@PostMapping("/users/")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	// Endpoint to delete a user by UID
	@DeleteMapping("/users/{uid}")
	public void deleteUserByUid(@PathVariable String uid) {
		userService.deleteUserByUid(uid);
	}
}
