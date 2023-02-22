package com.anonyshare.commentservice.domain;

import com.anonyshare.commentservice.entity.Comment;

public interface CommentDomainService {
    void validateAndInitiateComment(Comment comment);
    void updateCommentDescription(Comment comment, String content);
    void increaseLikesCount(Comment comment);
    void decreaseLikesCount(Comment comment);
    void increaseDislikesCount(Comment comment);
    void decreaseDislikeCount(Comment comment);

}
