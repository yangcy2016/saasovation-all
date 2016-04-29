package com.saasovation.agilepm.domain.model.forum;

public class Discussion {

	private DiscussionDescriptor descriptor;
	private DiscussionAvailability availability;

	public Discussion(DiscussionDescriptor discussionDescriptor, DiscussionAvailability availability) {
		this.descriptor = discussionDescriptor;
		this.availability = availability;
	}

	public DiscussionAvailability availability(){
		return this.availability;
	}

	public Discussion nowReady(DiscussionDescriptor descriptor) {
		return new Discussion(descriptor,DiscussionAvailability.READY);
	}
}
