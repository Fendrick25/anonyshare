package com.anonyshare.commentservice.repository;

import com.anonyshare.commentservice.entity.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentRepository {
    List<Comment> getComments(UUID postId);
    void deleteComment(UUID commentId);
    Comment saveComment(Comment comment);

    Comment findComment(UUID commentId);
    Comment updateCommentDescription(Comment comment);
    Comment updateCommentLikesAndDislikes(Comment comment);
}
