package com.example.product.service;

import com.example.product.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image uploadImage(MultipartFile file);
    List<Image> getAllImage();

    Image getImageById(Integer fileId);

    Image findImageById(Integer imageId);

    void deleteImageById(Integer imageId);
}
