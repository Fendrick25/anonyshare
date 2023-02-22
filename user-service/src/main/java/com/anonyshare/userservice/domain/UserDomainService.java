package com.anonyshare.userservice.domain;

import com.anonyshare.userservice.entity.User;

public interface UserDomainService {
    void validateAndInitiateUser(User user);
}
