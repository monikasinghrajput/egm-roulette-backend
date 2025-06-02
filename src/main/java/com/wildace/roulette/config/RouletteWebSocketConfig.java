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
package com.wildace.roulette.config;

import com.wildace.roulette.websocket.AdminWebSocketHandler;
import com.wildace.roulette.websocket.PlayerWebSocketHandler;
import com.wildace.roulette.websocket.TopperWebSocketHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// This configuration class sets up WebSocket handlers for different roles in the application.
// It registers the PlayerWebSocketHandler, TopperWebSocketHandler, and AdminWebSocketHandler
// to handle WebSocket connections at specific endpoints.
// The handlers are responsible for processing messages sent over the WebSocket connections
// and sending responses back to the clients.
@Configuration
@EnableWebSocket
public class RouletteWebSocketConfig implements WebSocketConfigurer {

	// This method registers the WebSocket handlers with their respective endpoints.
	// The setAllowedOrigins method allows cross-origin requests from any origin.
	// This is important for enabling WebSocket connections from different domains.
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(playerWebSocketHandler(), "/websocket/player").setAllowedOrigins("*");
        registry.addHandler(topperWebSocketHandler(), "/websocket/topper").setAllowedOrigins("*");
        registry.addHandler(adminWebSocketHandler(), "/websocket/admin").setAllowedOrigins("*");
    }

    // These methods create and return instances of the WebSocket handlers.
    // The @Bean annotation indicates that these methods produce Spring beans,
    // which can be injected into other components of the application.
    // Each handler is responsible for handling WebSocket messages for a specific role:
    // Player, Topper, and Admin.
    @Bean
    public PlayerWebSocketHandler playerWebSocketHandler() {
        return new PlayerWebSocketHandler();
    }

    @Bean
    public TopperWebSocketHandler topperWebSocketHandler() {
        return new TopperWebSocketHandler();
    }

    @Bean
    public AdminWebSocketHandler adminWebSocketHandler() {
        return new AdminWebSocketHandler();
    }
}