package com.saasovation.agilepm.domain.model.forum;

/**
 * @author : huanghy
 * @create : 2016/4/28 0028 ÏÂÎç 5:51
 * @since : ${VERSION}
 */
public class ProductDiscussion extends Discussion {

    public ProductDiscussion(DiscussionDescriptor discussionDescriptor, DiscussionAvailability availability) {
        super(discussionDescriptor, availability);
    }

    public static Discussion fromAvailability(DiscussionAvailability availability) {
        if(availability.isReady()){
            throw new IllegalArgumentException("cat not create ready");
        }
        DiscussionDescriptor descriptor = new DiscussionDescriptor(DiscussionDescriptor.UNDEFINED_ID);
        return new ProductDiscussion(descriptor,availability);
    }
}
