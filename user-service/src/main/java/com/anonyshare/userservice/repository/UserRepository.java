package com.anonyshare.userservice.repository;

import com.anonyshare.userservice.entity.User;

public interface UserRepository {
    User createUser(User user);
}
