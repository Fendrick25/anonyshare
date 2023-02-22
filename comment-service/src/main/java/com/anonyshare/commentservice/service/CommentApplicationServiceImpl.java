package com.anonyshare.commentservice.service;

import com.anonyshare.commentservice.domain.CommentDomainService;
import com.anonyshare.commentservice.dto.CommentDto;
import com.anonyshare.commentservice.dto.SingleCommentDto;
import com.anonyshare.commentservice.dto.command.CreateCommentCommand;
import com.anonyshare.commentservice.dto.command.UpdateCommentAttributeCommand;
import com.anonyshare.commentservice.dto.command.UpdateCommentDescriptionCommand;
import com.anonyshare.commentservice.entity.Comment;
import com.anonyshare.commentservice.entity.User;
import com.anonyshare.commentservice.event.CommentAttributeUpdatedEvent;
import com.anonyshare.commentservice.event.CommentCreatedEvent;
import com.anonyshare.commentservice.event.CommentDeletedEvent;
import com.anonyshare.commentservice.event.CommentUpdatedEvent;
import com.anonyshare.commentservice.exception.InvalidOperationException;
import com.anonyshare.commentservice.mapper.CommentDataMapper;
import com.anonyshare.commentservice.message.CommentMessagePublisher;
import com.anonyshare.commentservice.repository.CommentRepository;
import com.anonyshare.commentservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class CommentApplicationServiceImpl implements CommentApplicationService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentDomainService commentDomainService;
    private final CommentDataMapper commentDataMapper;
    private final CommentMessagePublisher commentMessagePublisher;
    private final Clock clock;

    @Override
    public List<CommentDto> getComments(UUID postId) {
        List<Comment> rootComments = commentRepository.getComments(postId);
        rootComments = rootComments.stream()
                .sorted(Comparator.comparing(Comment::getLikesCount).reversed())
                .collect(Collectors.toList());

        rootComments.forEach(rootComment -> {
            List<Comment> childComments = rootComment.getChildren();
            childComments.stream()
                    .sorted(Comparator.comparing(Comment::getLikesCount).reversed());
        });

        return rootComments.stream().map(commentDataMapper::commentToCommentDto).collect(Collectors.toList());
    }

    @Override
    public SingleCommentDto createComment(CreateCommentCommand createCommentCommand) {
        Comment comment = commentDataMapper.createCommentCommandToComment(createCommentCommand);
        commentDomainService.validateAndInitiateComment(comment);
        Comment savedComment = commentRepository.saveComment(comment);

        commentMessagePublisher.publish(CommentCreatedEvent.builder()
                        .comment(savedComment)
                        .createdAt(Instant.now(clock).getEpochSecond())
                .build());

        return commentDataMapper.commentToSingleCommentDto(savedComment);
    }

    @Override
    public SingleCommentDto findComment(UUID commentId) {
        return commentDataMapper.commentToSingleCommentDto(commentRepository.findComment(commentId));
    }

    @Override
    public SingleCommentDto updateCommentDescription(UpdateCommentDescriptionCommand updateCommentDescriptionCommand) {
        Comment oldComment = commentRepository.findComment(updateCommentDescriptionCommand.getCommentId());
        commentDomainService.updateCommentDescription(oldComment, updateCommentDescriptionCommand.getContent());
        Comment savedComment = commentRepository.updateCommentDescription(oldComment);

        commentMessagePublisher.publish(CommentUpdatedEvent.builder()
                .comment(savedComment)
                .createdAt(Instant.now(clock).getEpochSecond())
                .build());

        return commentDataMapper.commentToSingleCommentDto(commentRepository.saveComment(oldComment));
    }

    @Override
    public void deleteComment(UUID commentId) {
        commentRepository.deleteComment(commentId);
        commentMessagePublisher.publish(CommentDeletedEvent.builder()
                .commentId(commentId)
                .createdAt(Instant.now(clock).getEpochSecond())
                .build());
    }

    @Override
    public void updateCommentLikesOrDislikes(UpdateCommentAttributeCommand command) {
        Comment comment = commentRepository.findComment(command.getCommentId());
        User user = userRepository.findUser(command.getUserId());
        CommentInteractionType action = CommentInteractionType.valueOf(command.getAction());

        boolean hasLiked = user.getLikedComments().contains(command.getCommentId());
        boolean hasDisliked = user.getDislikedComments().contains(command.getCommentId());

        if (action == CommentInteractionType.LIKE) {
            if (!hasLiked && hasDisliked) {
                commentDomainService.increaseLikesCount(comment);
                commentDomainService.decreaseDislikeCount(comment);
                user.getLikedComments().add(comment.getId());
                user.getDislikedComments().remove(comment.getId());
                userRepository.updateUserLikedDislikedComments(user);
            }
            if (!hasLiked && !hasDisliked) {
                commentDomainService.increaseLikesCount(comment);
                user.getLikedComments().add(comment.getId());
                userRepository.updateUserLikedDislikedComments(user);
            }
        } else if (action == CommentInteractionType.REMOVE_LIKE) {
            if (hasLiked) {
                commentDomainService.decreaseLikesCount(comment);
                user.getLikedComments().remove(comment.getId());
                userRepository.updateUserLikedDislikedComments(user);
            }
        } else if (action == CommentInteractionType.DISLIKE) {
            if (hasLiked && !hasDisliked) {
                commentDomainService.increaseDislikesCount(comment);
                commentDomainService.decreaseLikesCount(comment);
                user.getDislikedComments().add(comment.getId());
                user.getLikedComments().remove(comment.getId());
                userRepository.updateUserLikedDislikedComments(user);
            }
            if (!hasLiked && !hasDisliked) {
                commentDomainService.increaseDislikesCount(comment);
                user.getDislikedComments().add(comment.getId());
                userRepository.updateUserLikedDislikedComments(user);
            }
        } else if (action == CommentInteractionType.REMOVE_DISLIKE) {
            if (hasDisliked) {
                commentDomainService.decreaseDislikeCount(comment);
                user.getDislikedComments().remove(comment.getId());
                userRepository.updateUserLikedDislikedComments(user);
            }
        }else{
            throw new InvalidOperationException("Invalid operation: " + action);
        }

        Comment savedComment = commentRepository.updateCommentLikesAndDislikes(comment);
        commentMessagePublisher.publish(CommentAttributeUpdatedEvent.builder()
                .commentId(savedComment.getId())
                .dislikesCount(savedComment.getDislikeCount())
                .likesCount(savedComment.getLikesCount())
                .createdAt(Instant.now(clock).getEpochSecond())
                .build());
    }
}
