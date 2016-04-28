package com.saasovation.agilepm.domain.model.product;


import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;

//维护一个有序的BacklogItem集合
public class ProductBackItem {

	private BacklogItemId backlogItemId;
	
	private int ordering;
	
	protected void reorderFrom(BacklogItemId anId, int anOrdering) {
		if(this.backlogItemId().equals(anId)){
			setOrdering(anOrdering);
		}else if(this.ordering()>=anOrdering){
			setOrdering(this.ordering()+1);
		}
	}
	
	
	public BacklogItemId backlogItemId(){
		return this.backlogItemId;
	}
	
	public int ordering(){
		return this.ordering;
	}
	
	public void setOrdering(int reOrdering){
		this.ordering = reOrdering;
	}

}
