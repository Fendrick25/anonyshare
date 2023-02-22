package com.anonyshare.commentservice.event;


import com.anonyshare.commentservice.entity.Comment;
import lombok.Builder;

public class CommentCreatedEvent extends CommentEvent{

    @Builder
    public CommentCreatedEvent(Comment comment, long createdAt) {
        super(comment, createdAt);
    }
}
