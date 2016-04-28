package com.saasovation.agilepm.domain.model.product;

public class BusinessPriorityTotals {
	private int totalBenefit = 0;
	private int totalPenalty = 0;
	private int totalCost    = 0;
	private int totalRisk    = 0;
	
	private int total;
	
	

	public BusinessPriorityTotals(int totalBenefit, int totalPenalty,
			int total,
			int totalCost, int totalRisk) {
		super();
		this.totalBenefit = totalBenefit;
		this.totalPenalty = totalPenalty;
		this.total = total;
		this.totalCost = totalCost;
		this.totalRisk = totalRisk;
	}



	public float totalCost() {
		// TODO Auto-generated method stub
		return 0;
	}

}
