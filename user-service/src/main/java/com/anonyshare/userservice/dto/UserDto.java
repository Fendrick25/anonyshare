package com.anonyshare.userservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@Getter
@Builder
@RequiredArgsConstructor
public class UserDto {
    private final UUID userId;
    private final String username;
    private final String email;
    private final String imageUrl;
}
