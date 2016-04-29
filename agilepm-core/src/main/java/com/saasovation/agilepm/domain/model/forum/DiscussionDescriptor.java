package com.saasovation.agilepm.domain.model.forum;

public class DiscussionDescriptor {

	private String discussionId;


	public DiscussionDescriptor(String discussionId) {
		this.discussionId = discussionId;
	}

	public static final String UNDEFINED_ID = "undefined";

	public String getDiscussionId() {
		return discussionId;
	}
}
