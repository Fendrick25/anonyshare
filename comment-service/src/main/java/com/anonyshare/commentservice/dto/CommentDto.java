package com.anonyshare.commentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CommentDto {
    private final UUID id;
    private final UUID postId;
    private final UserDto user;
    private final String content;
    private final int likesCount;
    private final int dislikeCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<CommentDto> replies;

}
