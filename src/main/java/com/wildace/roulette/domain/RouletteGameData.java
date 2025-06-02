package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "rouletteGameData")
public class RouletteGameData {

    @Id
    private String id;  // Optional MongoDB document ID

    private Group group;
    private List<Integer> coldNumbers;
    private List<Winner> lastWinners;
    private List<Integer> hotNumbers;
    private List<Win> history;
    private List<Stat> statistics;

    // Default constructor
    public RouletteGameData() {}

    // Parameterized constructor
    public RouletteGameData(Group group,
                            List<Integer> coldNumbers,
                            List<Winner> lastWinners,
                            List<Integer> hotNumbers,
                            List<Win> history,
                            List<Stat> statistics) {
        this.group = group;
        this.coldNumbers = coldNumbers;
        this.lastWinners = lastWinners;
        this.hotNumbers = hotNumbers;
        this.history = history;
        this.statistics = statistics;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Integer> getColdNumbers() {
        return coldNumbers;
    }

    public void setColdNumbers(List<Integer> coldNumbers) {
        this.coldNumbers = coldNumbers;
    }

    public List<Winner> getLastWinners() {
        return lastWinners;
    }

    public void setLastWinners(List<Winner> lastWinners) {
        this.lastWinners = lastWinners;
    }

    public List<Integer> getHotNumbers() {
        return hotNumbers;
    }

    public void setHotNumbers(List<Integer> hotNumbers) {
        this.hotNumbers = hotNumbers;
    }

    public List<Win> getHistory() {
        return history;
    }

    public void setHistory(List<Win> history) {
        this.history = history;
    }

    public List<Stat> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Stat> statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "RouletteGameData{" +
                "id='" + id + '\'' +
                ", group=" + group +
                ", coldNumbers=" + coldNumbers +
                ", lastWinners=" + lastWinners +
                ", hotNumbers=" + hotNumbers +
                ", history=" + history +
                ", statistics=" + statistics +
                '}';
    }
}
