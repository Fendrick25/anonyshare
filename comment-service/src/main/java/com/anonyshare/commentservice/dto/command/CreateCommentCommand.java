package com.anonyshare.commentservice.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateCommentCommand {
    private final UUID postId;
    private UUID parentCommentId;
    private final UUID userId;
    private final String content;
}
