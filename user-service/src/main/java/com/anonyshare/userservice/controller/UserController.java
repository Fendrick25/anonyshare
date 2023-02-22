package com.anonyshare.userservice.controller;

import com.anonyshare.userservice.dto.Data;
import com.anonyshare.userservice.dto.UserDto;
import com.anonyshare.userservice.dto.command.CreateUserCommand;
import com.anonyshare.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> findUser(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Data<UserDto>> createUser(@Valid CreateUserCommand createUserCommand){
        return new ResponseEntity<>(new Data<>(userService.createUser(createUserCommand)), HttpStatus.CREATED);
    }
}
