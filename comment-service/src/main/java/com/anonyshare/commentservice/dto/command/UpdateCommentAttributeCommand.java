package com.anonyshare.commentservice.dto.command;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class UpdateCommentAttributeCommand {
    private final UUID userId;
    private final UUID commentId;
    private final String action;
}
