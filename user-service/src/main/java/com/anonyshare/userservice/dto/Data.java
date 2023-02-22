package com.anonyshare.userservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Data<T>{
    private T data;
}
