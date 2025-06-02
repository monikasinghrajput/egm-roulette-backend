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

//DTO class to encapsulate the request body
public class UserDepositRequest {
 private String uid;
 private String egmId;
 private int credit;
 private String transBy;

 // Getters and Setters
 public String getUid() {
     return uid;
 }

 public void setUid(String uid) {
     this.uid = uid;
 }

 public String getEgmId() {
     return egmId;
 }

 public void setEgmId(String egmId) {
     this.egmId = egmId;
 }

 public int getCredit() {
     return credit;
 }

 public void setCredit(int credit) {
     this.credit = credit;
 }

 public String getTransBy() {
     return transBy;
 }

 public void setTransBy(String transBy) {
     this.transBy = transBy;
 }
}