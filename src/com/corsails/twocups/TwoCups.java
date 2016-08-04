package com.corsails.twocups;

public class TwoCups {
	private int cup1;
	private int cup2;
	private int target;
	
	public TwoCups() {
		this.cup1 = 0;
		this.cup2 = 0;
		this.target = 0;
	}
	
	public int getCup1() {
		return this.cup1;
	}
	
	public int getCup2() {
		return this.cup2;
	}
	
	public int getTarget() {
		return this.target;
	}
	
	public void setCup1(int cup1) {
		this.cup1 = cup1;
	}
	
	public void setCup2(int cup2) {
		this.cup2 = cup2;
	}
	
	public void setTarget(int target) {
		this.target = target;
	}
	
}
