package com.anonyshare.userservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudStorageService {
    String uploadImage(MultipartFile image);
}
