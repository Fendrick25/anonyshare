package com.anonyshare.commentservice.repository;

import com.anonyshare.commentservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<User, UUID> {
}
