package com.anonyshare.commentservice.domain;

import com.anonyshare.commentservice.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommentDomainServiceImpl implements CommentDomainService{

    private final Clock clock;

    @Override
    public void validateAndInitiateComment(Comment comment) {
        Instant now = Instant.now(clock);

        comment.setId(UUID.randomUUID());
        comment.setLikesCount(0);
        comment.setDislikeCount(0);
        comment.setCreatedAt(now.getEpochSecond());
        comment.setUpdatedAt(now.getEpochSecond());
    }

    @Override
    public void updateCommentDescription(Comment comment, String content) {
        comment.setContent(content);
        comment.setUpdatedAt(Instant.now(clock).getEpochSecond());
    }

    @Override
    public void increaseLikesCount(Comment comment) {
        comment.setLikesCount(comment.getLikesCount() + 1);
    }

    @Override
    public void decreaseLikesCount(Comment comment) {
        comment.setLikesCount(comment.getLikesCount() - 1);
    }

    @Override
    public void increaseDislikesCount(Comment comment) {
        comment.setDislikeCount(comment.getDislikeCount() + 1);
    }

    @Override
    public void decreaseDislikeCount(Comment comment) {
        comment.setDislikeCount(comment.getDislikeCount() - 1);
    }
}
