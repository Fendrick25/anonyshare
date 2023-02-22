package com.anonyshare.commentservice.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "comment-service")
public class CommentServiceConfigData {
    private String commentCreatedTopicName;
    private String commentUpdatedTopicName;
    private String commentDeletedTopicName;
    private String commentAttributeUpdatedTopicName;
}
