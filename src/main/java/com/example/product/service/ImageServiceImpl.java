package com.example.product.service;

import com.example.product.model.Image;
import com.example.product.repo.ImageRepo;
import com.example.product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private ProductRepo productRepo;

    public List<Image> getAllImage() {
        try {
            return imageRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Image getImageById(Integer imageId) {
        return imageRepo.findById(imageId).orElse(null);
    }

    @Override
    public Image findImageById(Integer imageId) {
        return imageRepo.findById(imageId).orElse(null);
    }

    @Override
    public void deleteImageById(Integer imageId) {
        productRepo.updateImageIdAsNull(imageId);
        imageRepo.deleteById(imageId);
    }

    @Override
    public Image uploadImage(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if(fileName.contains("..")) {
            throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
        }
        Image.ImageBuilder data = null;
        try {
            data = Image.builder().fileName(fileName).fileType(file.getContentType()).data(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image save = imageRepo.save(data.build());
        System.out.println(save);
        return save;
    }
}
