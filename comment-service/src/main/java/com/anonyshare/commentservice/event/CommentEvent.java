package com.anonyshare.commentservice.event;

import com.anonyshare.commentservice.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentEvent{
    private final Comment comment;
    private final long createdAt;
}
