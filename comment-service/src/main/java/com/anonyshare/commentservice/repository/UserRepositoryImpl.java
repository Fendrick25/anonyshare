package com.anonyshare.commentservice.repository;

import com.anonyshare.commentservice.entity.User;
import com.anonyshare.commentservice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final UserJpaRepository userJpaRepository;

    @Override
    public User saveUser(User user) {
       return userJpaRepository.save(user);
    }

    @Override
    public User findUser(UUID userId) {
        return findUserById(userId);
    }


    @Override
    public void updateUserLikedDislikedComments(User user) {
        User oldUser = findUserById(user.getId());
        oldUser.setLikedComments(user.getLikedComments());
        oldUser.setDislikedComments(user.getDislikedComments());
        userJpaRepository.save(oldUser);
    }

    private User findUserById(UUID userId){
        return userJpaRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found"));
    }
}
