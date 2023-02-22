package com.anonyshare.commentservice.event;


import com.anonyshare.commentservice.entity.Comment;
import lombok.Builder;

public class CommentUpdatedEvent extends CommentEvent{

    @Builder
    public CommentUpdatedEvent(Comment comment, long createdAt) {
        super(comment, createdAt);
    }
}
