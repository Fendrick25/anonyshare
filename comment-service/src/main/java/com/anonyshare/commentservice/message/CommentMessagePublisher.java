package com.anonyshare.commentservice.message;

import com.anonyshare.commentservice.event.CommentAttributeUpdatedEvent;
import com.anonyshare.commentservice.event.CommentCreatedEvent;
import com.anonyshare.commentservice.event.CommentDeletedEvent;
import com.anonyshare.commentservice.event.CommentUpdatedEvent;

public interface CommentMessagePublisher {
    void publish(CommentCreatedEvent commentCreatedEvent);
    void publish(CommentUpdatedEvent commentUpdatedEvent);
    void publish(CommentDeletedEvent commentDeletedEvent);
    void publish(CommentAttributeUpdatedEvent commentAttributeUpdatedEvent);
}
