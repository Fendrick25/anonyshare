package com.anonyshare.userservice.service;

import com.anonyshare.userservice.entity.User;

public interface UserSecurityService {
    void encryptPassword(User user);
}
