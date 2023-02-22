package com.anonyshare.userservice.dto.command;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateUserCommand {

    private final MultipartFile image;

    @NotNull(message = "username cannot be null")
    @Size(min = 3, max = 20, message = "Username must be greater than 2 characters and less than 20 characters")
    private final String username;

    @NotNull(message = "email cannot be null")
    @Email
    private final String email;

    @NotNull(message = "password cannot be null")
    @Size(min = 8, max = 20, message = "Password must be greater than 8 characters and less than 20 characters")
    private final String password;
}
