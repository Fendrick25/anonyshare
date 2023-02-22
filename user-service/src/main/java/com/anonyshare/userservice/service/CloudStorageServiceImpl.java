package com.anonyshare.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CloudStorageServiceImpl implements CloudStorageService{
    @Override
    public String uploadImage(MultipartFile image) {
        String url = "https://cloudservice.com/";

        if(image == null)
            return url + "default.png";

        String fileName = image.getOriginalFilename();
        int dotIndex = fileName.lastIndexOf(".");

        //Todo:: upload to Cloud Storage
        return url + UUID.randomUUID() + fileName.substring(dotIndex);
    }
}
