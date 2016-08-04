package com.corsails.twocups;

public class Cup {
	private int amount;
	private int capacity;
	
	// Constructor
	public Cup() {
		this.amount = 0;
		this.capacity = 0;
	}
	
	// Overloaded Constructor
	public Cup(int amount, int capacity) {
		this.amount = amount;
		this.capacity = capacity;
	}
	
	// Accessor methods
	public int getAmount() {
		return this.amount;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	// Mutator methods
	public void setAmount(int newAmount) {
		this.amount = newAmount;
	}
	
	public void setCapacity(int newCapacity) {
		this.capacity = newCapacity;
	}
	
	// Custom methods
	public boolean isFull() {
		return this.amount == this.capacity;
	}
	
	public boolean isEmpty() {
		return this.amount == 0;
	}
	
	public void fill() {
		// Returns the remainder of water in the other cup
		this.amount = this.capacity;
	}
	
	public void empty() {
		this.amount = 0;
	}
	
	public void transferFromOtherCup(Cup other) {
		int thisRemainingSpace = this.capacity - this.amount;
		
		if (other.getAmount() >= thisRemainingSpace) {
			other.setAmount(other.getAmount() - thisRemainingSpace);
			this.fill();
			if (other.getAmount() < 0)
				other.empty();
		}
		
		else {
			this.amount += other.getAmount();
			other.empty();
		}		
	}
}
