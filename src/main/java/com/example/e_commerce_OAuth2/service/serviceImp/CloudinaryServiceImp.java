package com.example.e_commerce_OAuth2.service.serviceImp;

import com.cloudinary.Cloudinary;
import com.example.e_commerce_OAuth2.service.CloudinaryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImp implements CloudinaryService {
    @Resource
    private Cloudinary cloudinary;
    @Override
    public String imageUpload(MultipartFile image) {
        try {
            HashMap<Object,Object>options=new HashMap<>();
            options.put("folder","productsImages");
            Map uploadFile =cloudinary.uploader().upload(image.getBytes(),options);
            String publicId=(String) uploadFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }

    }
}
