package com.example.e_commerce_OAuth2.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
//    @Value("${cloudinary.cloud-name}")
//    private String cloudName;
//
//    @Value("${cloudinary.api_key}")
//    private String apiKey;
//
//    @Value("${cloudinary.api_secret}")
//    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dg71mpjq7",
                "api_key", "964155391595828",
                "api_secret", "TjDT_fE4rLbPan-A1VqkpvnDo4I"));
    }
}
