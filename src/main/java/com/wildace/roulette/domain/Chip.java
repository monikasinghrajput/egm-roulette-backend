package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chips")
public class Chip {

    @Id
    private String _id;  // Optional MongoDB document ID
    private String id;  // Optional custom ID

    private String color;
    private int value;
    private String img;
    private boolean defaultChip;

    // Default constructor
    public Chip(String color, int value) {
		this.color = color;
		this.value = value;
	}

    // Parameterized constructor
    public Chip(String color, int value, String img, boolean defaultChip) {
        this.color = color;
        this.value = value;
        this.img = img;
        this.defaultChip = defaultChip;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isDefaultChip() {
        return defaultChip;
    }

    public void setDefaultChip(boolean defaultChip) {
        this.defaultChip = defaultChip;
    }

    @Override
    public String toString() {
        return "Chip{" +
                "id='" + id + '\'' +
                ", color='" + color + '\'' +
                ", value=" + value +
                ", img='" + img + '\'' +
                ", defaultChip=" + defaultChip +
                '}';
    }
}

