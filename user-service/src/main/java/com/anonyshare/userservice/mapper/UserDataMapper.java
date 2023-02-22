package com.anonyshare.userservice.mapper;

import com.anonyshare.userservice.dto.UserDto;
import com.anonyshare.userservice.dto.command.CreateUserCommand;
import com.anonyshare.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {
    public User createUserCommandToUser(CreateUserCommand createUserCommand){
        return User.builder()
                .username(createUserCommand.getUsername())
                .email(createUserCommand.getEmail())
                .password(createUserCommand.getPassword())
                .build();
    }

    public UserDto userToUserDto(User user){
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .username(user.getUsername())
                .imageUrl(user.getImageUrl())
                .build();
    }
}
