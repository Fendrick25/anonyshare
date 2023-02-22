package com.anonyshare.commentservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class SingleCommentDto {
    private final UUID id;
    private final UUID postId;
    private final UserDto user;
    private final String content;
    private final int likesCount;
    private final int dislikeCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
