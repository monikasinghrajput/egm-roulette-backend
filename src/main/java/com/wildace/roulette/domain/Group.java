package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "groups")
public class Group {

    @Id
    private String id;  // Optional MongoDB document ID

    private double groupFirstLine = 0.0;
    private double groupSecondLine = 0.0;
    private double groupThirdLine = 0.0;
    private double group1to12 = 0.0;
    private double group13to24 = 0.0;
    private double group25to36 = 0.0;
    private double groupBlack = 0.0;
    private double groupRed = 0.0;
    private double groupOdd = 0.0;
    private double groupEven = 0.0;
    private double group1to18 = 0.0;
    private double group19to36 = 0.0;

    // Default constructor
    public Group() {}

    // Parameterized constructor
    public Group(double groupFirstLine, double groupSecondLine, double groupThirdLine,
                 double group1to12, double group13to24, double group25to36,
                 double groupBlack, double groupRed, double groupOdd, double groupEven,
                 double group1to18, double group19to36) {
        this.groupFirstLine = groupFirstLine;
        this.groupSecondLine = groupSecondLine;
        this.groupThirdLine = groupThirdLine;
        this.group1to12 = group1to12;
        this.group13to24 = group13to24;
        this.group25to36 = group25to36;
        this.groupBlack = groupBlack;
        this.groupRed = groupRed;
        this.groupOdd = groupOdd;
        this.groupEven = groupEven;
        this.group1to18 = group1to18;
        this.group19to36 = group19to36;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getGroupFirstLine() {
        return groupFirstLine;
    }

    public void setGroupFirstLine(double groupFirstLine) {
        this.groupFirstLine = groupFirstLine;
    }

    public double getGroupSecondLine() {
        return groupSecondLine;
    }

    public void setGroupSecondLine(double groupSecondLine) {
        this.groupSecondLine = groupSecondLine;
    }

    public double getGroupThirdLine() {
        return groupThirdLine;
    }

    public void setGroupThirdLine(double groupThirdLine) {
        this.groupThirdLine = groupThirdLine;
    }

    public double getGroup1to12() {
        return group1to12;
    }

    public void setGroup1to12(double group1to12) {
        this.group1to12 = group1to12;
    }

    public double getGroup13to24() {
        return group13to24;
    }

    public void setGroup13to24(double group13to24) {
        this.group13to24 = group13to24;
    }

    public double getGroup25to36() {
        return group25to36;
    }

    public void setGroup25to36(double group25to36) {
        this.group25to36 = group25to36;
    }

    public double getGroupBlack() {
        return groupBlack;
    }

    public void setGroupBlack(double groupBlack) {
        this.groupBlack = groupBlack;
    }

    public double getGroupRed() {
        return groupRed;
    }

    public void setGroupRed(double groupRed) {
        this.groupRed = groupRed;
    }

    public double getGroupOdd() {
        return groupOdd;
    }

    public void setGroupOdd(double groupOdd) {
        this.groupOdd = groupOdd;
    }

    public double getGroupEven() {
        return groupEven;
    }

    public void setGroupEven(double groupEven) {
        this.groupEven = groupEven;
    }

    public double getGroup1to18() {
        return group1to18;
    }

    public void setGroup1to18(double group1to18) {
        this.group1to18 = group1to18;
    }

    public double getGroup19to36() {
        return group19to36;
    }

    public void setGroup19to36(double group19to36) {
        this.group19to36 = group19to36;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupFirstLine=" + groupFirstLine +
                ", groupSecondLine=" + groupSecondLine +
                ", groupThirdLine=" + groupThirdLine +
                ", group1to12=" + group1to12 +
                ", group13to24=" + group13to24 +
                ", group25to36=" + group25to36 +
                ", groupBlack=" + groupBlack +
                ", groupRed=" + groupRed +
                ", groupOdd=" + groupOdd +
                ", groupEven=" + groupEven +
                ", group1to18=" + group1to18 +
                ", group19to36=" + group19to36 +
                '}';
    }
}

