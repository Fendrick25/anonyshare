package com.anonyshare.commentservice.service;

import com.anonyshare.commentservice.dto.CommentDto;
import com.anonyshare.commentservice.dto.SingleCommentDto;
import com.anonyshare.commentservice.dto.command.CreateCommentCommand;
import com.anonyshare.commentservice.dto.command.UpdateCommentAttributeCommand;
import com.anonyshare.commentservice.dto.command.UpdateCommentDescriptionCommand;

import java.util.List;
import java.util.UUID;

public interface CommentApplicationService {
    List<CommentDto> getComments(UUID postId);
    SingleCommentDto createComment(CreateCommentCommand createCommentCommand);
    SingleCommentDto findComment(UUID commentId);
    SingleCommentDto updateCommentDescription(UpdateCommentDescriptionCommand updateCommentDescriptionCommand);

    void deleteComment(UUID commentId);
    void updateCommentLikesOrDislikes(UpdateCommentAttributeCommand updateCommentAttributeCommand);

}
