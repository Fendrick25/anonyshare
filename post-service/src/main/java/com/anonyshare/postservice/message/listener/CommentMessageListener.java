package com.anonyshare.postservice.message.listener;

public interface CommentMessageListener {
    void commentCreated();
    void commentUpdated();
    void commentDeleted();
    void commentAttributeUpdated();
}
