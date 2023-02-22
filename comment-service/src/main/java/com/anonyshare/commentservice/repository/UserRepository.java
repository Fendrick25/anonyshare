package com.anonyshare.commentservice.repository;

import com.anonyshare.commentservice.entity.User;

import java.util.Set;
import java.util.UUID;

public interface UserRepository {
    User saveUser(User user);
    User findUser(UUID userId);
    void updateUserLikedDislikedComments(User user);
}
