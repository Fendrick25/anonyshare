package com.anonyshare.commentservice.repository;

import com.anonyshare.commentservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentJpaRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByPostIdAndParentIsNull(UUID id);
}
