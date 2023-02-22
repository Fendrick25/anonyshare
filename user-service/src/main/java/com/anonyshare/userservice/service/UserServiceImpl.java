package com.anonyshare.userservice.service;

import com.anonyshare.userservice.domain.UserDomainService;
import com.anonyshare.userservice.dto.UserDto;
import com.anonyshare.userservice.dto.command.CreateUserCommand;
import com.anonyshare.userservice.entity.User;
import com.anonyshare.userservice.mapper.UserDataMapper;
import com.anonyshare.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;
    private final UserDomainService userDomainService;
    private final UserSecurityService userSecurityService;
    private final CloudStorageService cloudStorageService;

    @Override
    public UserDto createUser(CreateUserCommand createUserCommand) {
        User user = userDataMapper.createUserCommandToUser(createUserCommand);
        userDomainService.validateAndInitiateUser(user);
        userSecurityService.encryptPassword(user);

        String imageUrl = cloudStorageService.uploadImage(createUserCommand.getImage());
        user.setImageUrl(imageUrl);

        User savedUser =  userRepository.createUser(user);
        //Todo:: publish UserCreatedEvent to kafka
        return userDataMapper.userToUserDto(savedUser);
    }
}
