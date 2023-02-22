package com.anonyshare.commentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String username;
    private String imageUrl;
}
