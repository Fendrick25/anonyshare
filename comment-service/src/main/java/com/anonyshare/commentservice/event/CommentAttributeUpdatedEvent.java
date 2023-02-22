package com.anonyshare.commentservice.event;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class CommentAttributeUpdatedEvent {
    private final UUID commentId;
    private final int likesCount;
    private final int dislikesCount;
    private final long createdAt;
}
