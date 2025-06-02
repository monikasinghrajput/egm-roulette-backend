package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "adminClientData")
public class AdminClientData {

    @Id
    private String id;  // MongoDB document ID

    // Store actor references as actor path strings for persistence
    private String actorPath;
    private String clientPath;

    // Transient fields to hold actual ActorRef instances (not persisted)
    @Transient
    private transient akka.actor.ActorRef actor;

    @Transient
    private transient akka.actor.ActorRef client;

    private String name;

    // Default constructor
    public AdminClientData() {}

    // Parameterized constructor
    public AdminClientData(akka.actor.ActorRef actor, akka.actor.ActorRef client, String name) {
        this.actor = actor;
        this.client = client;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdminClientData{" +
                "id='" + id + '\'' +
                ", actorPath='" + actorPath + '\'' +
                ", clientPath='" + clientPath + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

