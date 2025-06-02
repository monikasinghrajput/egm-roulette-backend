package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wildace.roulette.domain.api.Bet;

import java.util.List;

@Document(collection = "clientData")
public class ClientData {

    @Id
    private String id;  // MongoDB document ID

    // Store actor references as strings (actor paths) for persistence
    private String actorPath;
    private String clientPath;

    // Transient actual ActorRef fields (not persisted)
    @Transient
    private transient akka.actor.ActorRef actor;

    @Transient
    private transient akka.actor.ActorRef client;

    private String uid = "-1";
    private String playerIp = "192.168.0.1";

    private List<Bet> betList;
    private List<WinBet> winningBets;

    private double balance = 0.0;

    // Default constructor
    public ClientData() {}

    // Parameterized constructor
    public ClientData(akka.actor.ActorRef actor, akka.actor.ActorRef client, String uid,
                      String playerIp, List<Bet> betList, List<WinBet> winningBets, double balance) {
        this.actor = actor;
        this.client = client;
        this.uid = uid;
        this.playerIp = playerIp;
        this.betList = betList;
        this.winningBets = winningBets;
        this.balance = balance;
        this.actorPath = actor != null ? actor.path().toString() : null;
        this.clientPath = client != null ? client.path().toString() : null;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActorPath() {
        return actorPath;
    }

    public void setActorPath(String actorPath) {
        this.actorPath = actorPath;
    }

    public String getClientPath() {
        return clientPath;
    }

    public void setClientPath(String clientPath) {
        this.clientPath = clientPath;
    }

    public akka.actor.ActorRef getActor() {
        return actor;
    }

    public void setActor(akka.actor.ActorRef actor) {
        this.actor = actor;
        this.actorPath = actor != null ? actor.path().toString() : null;
    }

    public akka.actor.ActorRef getClient() {
        return client;
    }

    public void setClient(akka.actor.ActorRef client) {
        this.client = client;
        this.clientPath = client != null ? client.path().toString() : null;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPlayerIp() {
        return playerIp;
    }

    public void setPlayerIp(String playerIp) {
        this.playerIp = playerIp;
    }

    public List<Bet> getBetList() {
        return betList;
    }

    public void setBetList(List<Bet> betList) {
        this.betList = betList;
    }

    public List<WinBet> getWinningBets() {
        return winningBets;
    }

    public void setWinningBets(List<WinBet> winningBets) {
        this.winningBets = winningBets;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "id='" + id + '\'' +
                ", actorPath='" + actorPath + '\'' +
                ", clientPath='" + clientPath + '\'' +
                ", uid='" + uid + '\'' +
                ", playerIp='" + playerIp + '\'' +
                ", betList=" + betList +
                ", winningBets=" + winningBets +
                ", balance=" + balance +
                '}';
    }
}
