package com.example.e_commerce_OAuth2.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    public String imageUpload(MultipartFile image);
}
