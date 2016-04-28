package com.saasovation.agilepm.domain.model.forum;

public class Discussion {

	private DiscussionDescriptor descriptor;
	private DiscussionAvailability availability;

	public Discussion(DiscussionDescriptor discussionDescriptor, DiscussionAvailability availability) {
		this.descriptor = discussionDescriptor;
		this.availability = availability;
	}
}
