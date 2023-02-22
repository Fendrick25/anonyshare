 package com.anonyshare.commentservice.repository;

import com.anonyshare.commentservice.entity.Comment;
import com.anonyshare.commentservice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository{

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public List<Comment> getComments(UUID postId) {
        return commentJpaRepository.findByPostIdAndParentIsNull(postId);
    }

    @Override
    public void deleteComment(UUID commentId) {
        commentJpaRepository.delete(findCommentById(commentId));
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentJpaRepository.save(comment);
    }

    @Override
    public Comment findComment(UUID commentId) {
        return findCommentById(commentId);
    }

    @Override
    @Transactional
    public Comment updateCommentDescription(Comment comment) {
        Comment commentById = findCommentById(comment.getId());
        commentById.setContent(comment.getContent());
        commentById.setUpdatedAt(comment.getUpdatedAt());
        return commentById;
    }

    @Override
    @Transactional
    public Comment updateCommentLikesAndDislikes(Comment comment) {
        Comment commentById = findCommentById(comment.getId());
        commentById.setLikesCount(comment.getLikesCount());
        commentById.setDislikeCount(comment.getDislikeCount());
        return commentById;
    }


    private Comment findCommentById(UUID commentId){
        return commentJpaRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment with id: " + commentId + " not found"));
    }
}
