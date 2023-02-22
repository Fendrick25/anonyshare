package com.anonyshare.commentservice.event;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class CommentDeletedEvent {
    private final UUID commentId;
    private final long createdAt;
}
