package com.wildace.roulette.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "tableLimits")
public class TableLimit {

    @Id
    private String _id;  // Optional MongoDB document ID
    private String id;  // Optional custom ID

    private List<Chip> chips = List.of(
			new Chip("Red", 10),
			new Chip("Black", 50),
			new Chip("Green", 100),
			new Chip("Blue", 500),
			new Chip("Yellow", 1000)
	);

    private int minBet = 10;
    private int maxBet = 10000000;
    private int minSideBet = 0;
    private int maxSideBet = 0;
    private int minStraightUpBet = 10;
    private int maxStraightUpBet = 25000;
    private int minSplitBet = 10;
    private int maxSplitBet = 50000;
    private int minStreetBet = 10;
    private int maxStreetBet = 75000;
    private int minCornerBet = 10;
    private int maxCornerBet = 100000;
    private int minLineBet = 10;
    private int maxLineBet = 150000;
    private int minDozenColumnBet = 10;
    private int maxDozenColumnBet = 300000;
    private int minOutsideBet = 10;
    private int maxOutsideBet = 500000;

    // Default constructor
    public TableLimit() {}

    // Parameterized constructor
    public TableLimit(List<Chip> chips,
                      int minBet, int maxBet,
                      int minSideBet, int maxSideBet,
                      int minStraightUpBet, int maxStraightUpBet,
                      int minSplitBet, int maxSplitBet,
                      int minStreetBet, int maxStreetBet,
                      int minCornerBet, int maxCornerBet,
                      int minLineBet, int maxLineBet,
                      int minDozenColumnBet, int maxDozenColumnBet,
                      int minOutsideBet, int maxOutsideBet) {
        this.chips = chips;
        this.minBet = minBet;
        this.maxBet = maxBet;
        this.minSideBet = minSideBet;
        this.maxSideBet = maxSideBet;
        this.minStraightUpBet = minStraightUpBet;
        this.maxStraightUpBet = maxStraightUpBet;
        this.minSplitBet = minSplitBet;
        this.maxSplitBet = maxSplitBet;
        this.minStreetBet = minStreetBet;
        this.maxStreetBet = maxStreetBet;
        this.minCornerBet = minCornerBet;
        this.maxCornerBet = maxCornerBet;
        this.minLineBet = minLineBet;
        this.maxLineBet = maxLineBet;
        this.minDozenColumnBet = minDozenColumnBet;
        this.maxDozenColumnBet = maxDozenColumnBet;
        this.minOutsideBet = minOutsideBet;
        this.maxOutsideBet = maxOutsideBet;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Chip> getChips() {
        return chips;
    }

    public void setChips(List<Chip> chips) {
        this.chips = chips;
    }

    public int getMinBet() {
        return minBet;
    }

    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }

    public int getMaxBet() {
        return maxBet;
    }

    public void setMaxBet(int maxBet) {
        this.maxBet = maxBet;
    }

    public int getMinSideBet() {
        return minSideBet;
    }

    public void setMinSideBet(int minSideBet) {
        this.minSideBet = minSideBet;
    }

    public int getMaxSideBet() {
        return maxSideBet;
    }

    public void setMaxSideBet(int maxSideBet) {
        this.maxSideBet = maxSideBet;
    }

    public int getMinStraightUpBet() {
        return minStraightUpBet;
    }

    public void setMinStraightUpBet(int minStraightUpBet) {
        this.minStraightUpBet = minStraightUpBet;
    }

    public int getMaxStraightUpBet() {
        return maxStraightUpBet;
    }

    public void setMaxStraightUpBet(int maxStraightUpBet) {
        this.maxStraightUpBet = maxStraightUpBet;
    }

    public int getMinSplitBet() {
        return minSplitBet;
    }

    public void setMinSplitBet(int minSplitBet) {
        this.minSplitBet = minSplitBet;
    }

    public int getMaxSplitBet() {
        return maxSplitBet;
    }

    public void setMaxSplitBet(int maxSplitBet) {
        this.maxSplitBet = maxSplitBet;
    }

    public int getMinStreetBet() {
        return minStreetBet;
    }

    public void setMinStreetBet(int minStreetBet) {
        this.minStreetBet = minStreetBet;
    }

    public int getMaxStreetBet() {
        return maxStreetBet;
    }

    public void setMaxStreetBet(int maxStreetBet) {
        this.maxStreetBet = maxStreetBet;
    }

    public int getMinCornerBet() {
        return minCornerBet;
    }

    public void setMinCornerBet(int minCornerBet) {
        this.minCornerBet = minCornerBet;
    }

    public int getMaxCornerBet() {
        return maxCornerBet;
    }

    public void setMaxCornerBet(int maxCornerBet) {
        this.maxCornerBet = maxCornerBet;
    }

    public int getMinLineBet() {
        return minLineBet;
    }

    public void setMinLineBet(int minLineBet) {
        this.minLineBet = minLineBet;
    }

    public int getMaxLineBet() {
        return maxLineBet;
    }

    public void setMaxLineBet(int maxLineBet) {
        this.maxLineBet = maxLineBet;
    }

    public int getMinDozenColumnBet() {
        return minDozenColumnBet;
    }

    public void setMinDozenColumnBet(int minDozenColumnBet) {
        this.minDozenColumnBet = minDozenColumnBet;
    }

    public int getMaxDozenColumnBet() {
        return maxDozenColumnBet;
    }

    public void setMaxDozenColumnBet(int maxDozenColumnBet) {
        this.maxDozenColumnBet = maxDozenColumnBet;
    }

    public int getMinOutsideBet() {
        return minOutsideBet;
    }

    public void setMinOutsideBet(int minOutsideBet) {
        this.minOutsideBet = minOutsideBet;
    }

    public int getMaxOutsideBet() {
        return maxOutsideBet;
    }

    public void setMaxOutsideBet(int maxOutsideBet) {
        this.maxOutsideBet = maxOutsideBet;
    }

    @Override
    public String toString() {
        return "TableLimit{" +
                "chips=" + chips +
                ", minBet=" + minBet +
                ", maxBet=" + maxBet +
                ", minSideBet=" + minSideBet +
                ", maxSideBet=" + maxSideBet +
                ", minStraightUpBet=" + minStraightUpBet +
                ", maxStraightUpBet=" + maxStraightUpBet +
                ", minSplitBet=" + minSplitBet +
                ", maxSplitBet=" + maxSplitBet +
                ", minStreetBet=" + minStreetBet +
                ", maxStreetBet=" + maxStreetBet +
                ", minCornerBet=" + minCornerBet +
                ", maxCornerBet=" + maxCornerBet +
                ", minLineBet=" + minLineBet +
                ", maxLineBet=" + maxLineBet +
                ", minDozenColumnBet=" + minDozenColumnBet +
                ", maxDozenColumnBet=" + maxDozenColumnBet +
                ", minOutsideBet=" + minOutsideBet +
                ", maxOutsideBet=" + maxOutsideBet +
                '}';
    }
}
