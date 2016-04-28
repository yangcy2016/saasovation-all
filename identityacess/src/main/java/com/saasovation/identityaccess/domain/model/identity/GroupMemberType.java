package com.saasovation.identityaccess.domain.model.identity;

public enum GroupMemberType {
	USER{
		public boolean isUser(){
			return true;
		}
	},
	GROUP{
		public boolean isGroup(){
			return true;
		}
	};
	
	public boolean isUser(){
		return false;
	}
	
	public boolean isGroup(){
		return false;
	}
}
