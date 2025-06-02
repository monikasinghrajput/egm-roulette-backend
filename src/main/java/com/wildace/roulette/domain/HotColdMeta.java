package com.wildace.roulette.domain;

public class HotColdMeta {

	private int count;
	private String color;
	
	public HotColdMeta(int count, String color) {
		this.count = count;
		this.color = color;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
