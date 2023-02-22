package com.anonyshare.userservice.service;

import com.anonyshare.userservice.dto.UserDto;
import com.anonyshare.userservice.dto.command.CreateUserCommand;

public interface UserService {
    UserDto createUser(CreateUserCommand createUserCommand);
}
