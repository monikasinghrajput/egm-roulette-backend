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
package com.wildace.roulette.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.wildace.roulette.domain.documents.User;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User findOneAndUpdate(String id, User updatedUser) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        
        if (updatedUser.getUid() != null) update.set("uid", updatedUser.getUid());
        if (updatedUser.getRole() != null) update.set("role", updatedUser.getRole());
        if (updatedUser.getIsPlaying() != null) update.set("isPlaying", updatedUser.getIsPlaying());
        // Add other fields you want to update here

        return mongoTemplate.findAndModify(query, update, User.class);
    }
    
    @Override
    public User findOne(String uid) {
		Query query = new Query(Criteria.where("uid").is(uid));
		return mongoTemplate.findOne(query, User.class);
	}
    
	@Override
	public User updateOne(String uid, User updatedUser) {
		Query query = new Query(Criteria.where("uid").is(uid));
		Update update = new Update();
		
		if (updatedUser.getRole() != null) update.set("role", updatedUser.getRole());
		if (updatedUser.getIsPlaying() != null) update.set("isPlaying", updatedUser.getIsPlaying());
		// Add other fields you want to update here

		return mongoTemplate.findAndModify(query, update, User.class);
	}
	
	@Override
	public List<User> findAll() {
		return mongoTemplate.findAll(User.class);
	}
}