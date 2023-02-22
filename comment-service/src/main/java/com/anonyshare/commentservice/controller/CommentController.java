package com.anonyshare.commentservice.controller;

import com.anonyshare.commentservice.dto.CommentDto;
import com.anonyshare.commentservice.dto.Data;
import com.anonyshare.commentservice.dto.SingleCommentDto;
import com.anonyshare.commentservice.dto.command.CreateCommentCommand;
import com.anonyshare.commentservice.dto.command.UpdateCommentAttributeCommand;
import com.anonyshare.commentservice.dto.command.UpdateCommentDescriptionCommand;
import com.anonyshare.commentservice.service.CommentApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comments")
public class CommentController {

    private final CommentApplicationService commentApplicationService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<Data<List<CommentDto>>> getComments(@PathVariable("postId") UUID postId){
        return new ResponseEntity<>(new Data<>(commentApplicationService.getComments(postId)), HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Data<SingleCommentDto>> findComment(@PathVariable("commentId") UUID commentId){
        return new ResponseEntity<>(new Data<>(commentApplicationService.findComment(commentId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Data<SingleCommentDto>> createComment(@RequestBody CreateCommentCommand createCommentCommand){
        return new ResponseEntity<>(new Data<>(commentApplicationService.createComment(createCommentCommand)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Data<SingleCommentDto>> updateCommentDescription(@RequestBody UpdateCommentDescriptionCommand updateCommentDescriptionCommand){
        return new ResponseEntity<>(new Data<>(commentApplicationService.updateCommentDescription(updateCommentDescriptionCommand)), HttpStatus.OK);
    }

    @PutMapping("/attributes")
    public ResponseEntity<Void> updateCommentLikeOrDislike(@RequestBody UpdateCommentAttributeCommand updateCommentAttributeCommand){
        commentApplicationService.updateCommentLikesOrDislikes(updateCommentAttributeCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") UUID commentId){
        commentApplicationService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
