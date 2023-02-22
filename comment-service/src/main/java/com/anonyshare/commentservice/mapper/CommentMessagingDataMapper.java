package com.anonyshare.commentservice.mapper;

import com.anonyshare.commentservice.event.CommentAttributeUpdatedEvent;
import com.anonyshare.commentservice.event.CommentCreatedEvent;
import com.anonyshare.commentservice.event.CommentDeletedEvent;
import com.anonyshare.commentservice.event.CommentUpdatedEvent;
import com.anonyshare.kafka.model.CommentAttributeUpdatedAvroModel;
import com.anonyshare.kafka.model.CommentAvroModel;
import com.anonyshare.kafka.model.CommentDeletedAvroModel;
import com.anonyshare.kafka.model.CommentUpdatedAvroModel;
import org.springframework.stereotype.Component;


@Component
public class CommentMessagingDataMapper {
    public CommentAvroModel commentCreatedEventToCommentAvroModel(CommentCreatedEvent commentCreatedEvent){
        return CommentAvroModel.newBuilder()
                .setId(commentCreatedEvent.getComment().getId())
                .setPostId(commentCreatedEvent.getComment().getPostId())
                .setContent(commentCreatedEvent.getComment().getContent())
                .setLikesCount(commentCreatedEvent.getComment().getLikesCount())
                .setDislikeCount(commentCreatedEvent.getComment().getDislikeCount())
                .setCreatedAt(commentCreatedEvent.getComment().getCreatedAt())
                .setUpdatedAt(commentCreatedEvent.getComment().getUpdatedAt())
                .setUserBuilder(CommentAvroModel.newBuilder()
                        .getUserBuilder()
                        .setId(commentCreatedEvent.getComment().getUser().getId().toString())
                        .setImageUrl(commentCreatedEvent.getComment().getUser().getImageUrl())
                        .setUsername(commentCreatedEvent.getComment().getUser().getUsername()))
                .build();
    }

    public CommentDeletedAvroModel commentDeletedEventToCommentAvroModel(CommentDeletedEvent commentDeletedEvent){
        return CommentDeletedAvroModel.newBuilder()
                .setId(commentDeletedEvent.getCommentId())
                .build();
    }

    public CommentAttributeUpdatedAvroModel commentAttributeUpdatedEventToCommentAttributeUpdatedAvroModel(CommentAttributeUpdatedEvent commentAttributeUpdatedEvent){
        return CommentAttributeUpdatedAvroModel.newBuilder()
                .setId(commentAttributeUpdatedEvent.getCommentId())
                .setLikesCount(commentAttributeUpdatedEvent.getLikesCount())
                .setDislikeCount(commentAttributeUpdatedEvent.getDislikesCount())
                .build();
    }

    public CommentUpdatedAvroModel commentUpdatedEventToCommentUpdatedAvroModel(CommentUpdatedEvent commentUpdatedEvent){
        return CommentUpdatedAvroModel.newBuilder()
                .setId(commentUpdatedEvent.getComment().getId())
                .setContent(commentUpdatedEvent.getComment().getContent())
                .setUpdatedAt(commentUpdatedEvent.getComment().getUpdatedAt())
                .build();
    }
}
