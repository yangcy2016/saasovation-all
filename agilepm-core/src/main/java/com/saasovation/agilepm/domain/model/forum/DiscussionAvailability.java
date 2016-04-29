package com.saasovation.agilepm.domain.model.forum;

public enum DiscussionAvailability {
	ADD_ON_NOT_ENABLED,
	NOT_REQUESTED,
	REQUESTED{
		public boolean isRequested(){
			return true;
		}
	},
	READY{
		public boolean isReady(){
			return false;
		}
	},
	FAILED;
	public boolean isRequested(){
		return false;
	}
	public boolean isReady(){
		return false;
	}
}
