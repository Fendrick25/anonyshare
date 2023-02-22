package com.anonyshare.postservice.message.listener;

import com.anonyshare.kafka.consumer.KafkaConsumer;
import com.anonyshare.kafka.model.CommentUpdatedAvroModel;
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
public class CommentUpdatedKafkaMessageListener implements KafkaConsumer<CommentUpdatedAvroModel> {

    @Override
    @KafkaListener(id = "${kafka-consumer-config.comment-updated-consumer-group-id}",
            topics = "${post-service.comment-updated-topic-name}")
    public void receive(@Payload List<CommentUpdatedAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of comments updated received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(commentUpdatedAvroModel -> {
            log.info("id: {}, content: {}", commentUpdatedAvroModel.getId(), commentUpdatedAvroModel.getContent());
        });
    }
}
