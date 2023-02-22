package com.anonyshare.userservice.domain;

import com.anonyshare.userservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService{

    private final Clock clock;

    @Override
    public void validateAndInitiateUser(User user) {
        user.setId(UUID.randomUUID());
        user.setUserId(UUID.randomUUID());
        user.setCreatedAt(Instant.now(clock).getEpochSecond());
    }
}
