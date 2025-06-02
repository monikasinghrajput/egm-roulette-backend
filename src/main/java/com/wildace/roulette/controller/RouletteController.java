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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.wildace.roulette.services.RouletteService;

import org.springframework.web.context.request.WebRequest;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/spa/v1/roulette")
public class RouletteController {

    private static final Logger logger = LoggerFactory.getLogger(RouletteController.class);

    private RouletteService rouletteService;
    
    public RouletteController(RouletteService rouletteService) {
		this.rouletteService = rouletteService;
	}

    @GetMapping("/example")
    public String example(HttpServletRequest request) {
        // Access request details
        String clientIp = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        // Return a response
        return "Client IP: " + clientIp + ", User-Agent: " + userAgent;
    }
    

}