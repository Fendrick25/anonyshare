package com.anonyshare.postservice.message.listener;

import com.anonyshare.kafka.consumer.KafkaConsumer;
import com.anonyshare.kafka.model.CommentAttributeUpdatedAvroModel;
import com.anonyshare.kafka.model.CommentAvroModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentAttributeUpdatedKafkaMessageListener implements KafkaConsumer<CommentAttributeUpdatedAvroModel> {

    @Override
    @KafkaListener(id = "${kafka-consumer-config.comment-attribute-updated-consumer-group-id}",
            topics = "${post-service.comment-attribute-updated-topic-name}")
    public void receive(@Payload List<CommentAttributeUpdatedAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of comments attributes received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(commentAttributeUpdatedAvroModel -> {
            log.info("id: {}, likes: {}, dislikes: {}", commentAttributeUpdatedAvroModel.getId(), commentAttributeUpdatedAvroModel.getLikesCount(), commentAttributeUpdatedAvroModel.getDislikeCount());
        });
    }

}
