package com.anonyshare.userservice.service;

import com.anonyshare.userservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSecurityServiceImpl implements UserSecurityService{
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void encryptPassword(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }
}
