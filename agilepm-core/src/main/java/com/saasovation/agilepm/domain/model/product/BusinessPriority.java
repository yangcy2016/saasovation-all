package com.saasovation.agilepm.domain.model.product;

public class BusinessPriority {
	private BusinessPriorityRating ratings;
	
	
	public BusinessPriority() {
		super();
	}
	//main constructor
	public BusinessPriority(BusinessPriorityRating ratings) {
		super();
		this.setRating(ratings);
	}
	//copy constructor
	public BusinessPriority(BusinessPriority priority) {
		this(priority.ratings);
	}
	
	public float costPercentage(BusinessPriorityTotals totals){
		return (float)100*this.ratings.cost()/totals.totalCost();
	}
	
	
	
	
	//Self Encapsulation
	private void setRating(BusinessPriorityRating aRatings){
		if(aRatings==null)
			throw new IllegalArgumentException("ratings are required");
		this.ratings = aRatings;
	}
	public BusinessPriorityRating rating() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
