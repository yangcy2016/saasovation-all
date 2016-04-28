package com.saasovation.agilepm.domain.model.product;

public class BusinessPriorityRating {

	private int benefit;
	private int cost;
	private int penalty;
	private int risk;
	
	
	public int cost() {
		return this.cost;
	}
	
	public int benefit(){
		return this.benefit;
	}
	
	public int penalty(){
		return this.penalty;
	}
	
	public int risk(){
		return this.risk;
	}

}
