package com.anonyshare.commentservice.message;

import com.anonyshare.commentservice.config.CommentServiceConfigData;
import com.anonyshare.commentservice.event.CommentAttributeUpdatedEvent;
import com.anonyshare.commentservice.event.CommentCreatedEvent;
import com.anonyshare.commentservice.event.CommentDeletedEvent;
import com.anonyshare.commentservice.event.CommentUpdatedEvent;
import com.anonyshare.commentservice.mapper.CommentMessagingDataMapper;
import com.anonyshare.kafka.model.CommentAttributeUpdatedAvroModel;
import com.anonyshare.kafka.model.CommentAvroModel;
import com.anonyshare.kafka.model.CommentDeletedAvroModel;
import com.anonyshare.kafka.model.CommentUpdatedAvroModel;
import com.anonyshare.kafka.producer.KafkaMessageHelper;
import com.anonyshare.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommentKafkaMessagePublisher implements CommentMessagePublisher{

    private final KafkaProducer<String, CommentAvroModel> commentProducer;
    private final KafkaProducer<String, CommentDeletedAvroModel> commentDeletedProducer;
    private final KafkaProducer<String, CommentAttributeUpdatedAvroModel> commentAttributeUpdatedProducer;
    private final KafkaProducer<String, CommentUpdatedAvroModel> commentUpdatedAvroModelProducer;
    private final CommentServiceConfigData commentServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final CommentMessagingDataMapper commentMessagingDataMapper;

    @Override
    public void publish(CommentCreatedEvent commentCreatedEvent) {
        CommentAvroModel commentAvroModel = commentMessagingDataMapper.commentCreatedEventToCommentAvroModel(commentCreatedEvent);
        String id = UUID.randomUUID().toString();
        try{
            commentProducer.send(commentServiceConfigData.getCommentCreatedTopicName(),
                    id,
                    commentAvroModel,
                    kafkaMessageHelper.getKafkaCallback(commentServiceConfigData.getCommentCreatedTopicName(),
                            commentAvroModel,
                            commentAvroModel.getId().toString(),
                            "CustomerAvroModel"));
        }catch (Exception e){
            log.error("Error while sending CustomerAvroModel to kafka for commentId id: {} and id: {}," +
                    " error: {}", commentAvroModel.getId(), id, e.getMessage());
        }
    }

    @Override
    public void publish(CommentUpdatedEvent commentUpdatedEvent) {
        CommentUpdatedAvroModel commentUpdatedAvroModel = commentMessagingDataMapper.commentUpdatedEventToCommentUpdatedAvroModel(commentUpdatedEvent);
        String id = UUID.randomUUID().toString();
        try{
            commentUpdatedAvroModelProducer.send(commentServiceConfigData.getCommentUpdatedTopicName(),
                    id,
                    commentUpdatedAvroModel,
                    kafkaMessageHelper.getKafkaCallback(commentServiceConfigData.getCommentUpdatedTopicName(),
                            commentUpdatedAvroModel,
                            commentUpdatedAvroModel.getId().toString(),
                            "CommentUpdatedAvroModel"));
        }catch (Exception e){
            log.error("Error while sending CommentUpdatedAvroModel to kafka for commentId id: {} and id: {}," +
                    " error: {}", commentUpdatedAvroModel.getId(), id, e.getMessage());
        }
    }

    @Override
    public void publish(CommentDeletedEvent commentDeletedEvent) {
        CommentDeletedAvroModel commentDeletedAvroModel = commentMessagingDataMapper.commentDeletedEventToCommentAvroModel(commentDeletedEvent);
        String id = UUID.randomUUID().toString();
        try{
            commentDeletedProducer.send(commentServiceConfigData.getCommentDeletedTopicName(),
                    id,
                    commentDeletedAvroModel,
                    kafkaMessageHelper.getKafkaCallback(commentServiceConfigData.getCommentDeletedTopicName(),
                            commentDeletedAvroModel,
                            commentDeletedAvroModel.getId().toString(),
                            "CommentDeletedAvroModel"));
        }catch (Exception e){
            log.error("Error while sending CommentDeletedAvroModel to kafka for commentId id: {} and id: {}," +
                    " error: {}", commentDeletedAvroModel.getId(), id, e.getMessage());
        }
    }

    @Override
    public void publish(CommentAttributeUpdatedEvent commentAttributeUpdatedEvent) {
        CommentAttributeUpdatedAvroModel commentAttributeUpdatedAvroModel = commentMessagingDataMapper.commentAttributeUpdatedEventToCommentAttributeUpdatedAvroModel(commentAttributeUpdatedEvent);
        String id = UUID.randomUUID().toString();
        try{
            commentAttributeUpdatedProducer.send(commentServiceConfigData.getCommentAttributeUpdatedTopicName(),
                    id,
                    commentAttributeUpdatedAvroModel,
                    kafkaMessageHelper.getKafkaCallback(commentServiceConfigData.getCommentAttributeUpdatedTopicName(),
                            commentAttributeUpdatedAvroModel,
                            commentAttributeUpdatedAvroModel.getId().toString(),
                            "CommentAttributeUpdatedAvroModel"));
        }catch (Exception e){
            log.error("Error while sending CommentAttributeUpdatedAvroModel to kafka for commentId id: {} and id: {}," +
                    " error: {}", commentAttributeUpdatedAvroModel.getId(), id, e.getMessage());
        }
    }
}
