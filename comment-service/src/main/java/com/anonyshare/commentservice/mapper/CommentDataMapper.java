package com.anonyshare.commentservice.mapper;

import com.anonyshare.commentservice.dto.CommentDto;
import com.anonyshare.commentservice.dto.SingleCommentDto;
import com.anonyshare.commentservice.dto.UserDto;
import com.anonyshare.commentservice.dto.command.CreateCommentCommand;
import com.anonyshare.commentservice.entity.Comment;
import com.anonyshare.commentservice.entity.User;
import com.anonyshare.commentservice.event.CommentCreatedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.stream.Collectors;

@Component
public class CommentDataMapper {

    public CommentDto commentToCommentDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .postId(comment.getPostId())
                .content(comment.getContent())
                .likesCount(comment.getLikesCount())
                .user(UserDto.builder()
                        .id(comment.getUser().getId())
                        .username(comment.getUser().getUsername())
                        .imageUrl(comment.getUser().getImageUrl())
                        .build())
                .dislikeCount(comment.getDislikeCount())
                .createdAt(Instant.ofEpochSecond(comment.getCreatedAt())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .updatedAt(Instant.ofEpochSecond(comment.getUpdatedAt())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .replies(comment.getChildren().stream()
                        .map(this::commentToCommentDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public SingleCommentDto commentToSingleCommentDto(Comment comment){
        return SingleCommentDto.builder()
                .id(comment.getId())
                .postId(comment.getPostId())
                .content(comment.getContent())
                .likesCount(comment.getLikesCount())
                .user(UserDto.builder()
                        .id(comment.getUser().getId())
                        .username(comment.getUser().getUsername())
                        .imageUrl(comment.getUser().getImageUrl())
                        .build())
                .dislikeCount(comment.getDislikeCount())
                .createdAt(Instant.ofEpochSecond(comment.getCreatedAt())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .updatedAt(Instant.ofEpochSecond(comment.getUpdatedAt())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .build();
    }

    public Comment createCommentCommandToComment(CreateCommentCommand createCommentCommand){
        return Comment.builder()
                .postId(createCommentCommand.getPostId())
                .parent(Comment.builder()
                        .id(createCommentCommand.getParentCommentId())
                        .build())
                .user(User.builder()
                        .id(createCommentCommand.getUserId())
                        .build())
                .content(createCommentCommand.getContent())
                .build();
    }
}
