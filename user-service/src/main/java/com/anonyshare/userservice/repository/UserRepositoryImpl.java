package com.anonyshare.userservice.repository;

import com.anonyshare.userservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final UserJpaRepository userJpaRepository;

    @Override
    public User createUser(User user) {
        return userJpaRepository.save(user);
    }
}
