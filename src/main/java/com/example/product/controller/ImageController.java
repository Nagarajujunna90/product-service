package com.example.product.controller;

import com.example.product.model.Image;
import com.example.product.service.ImageService;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("http://3.88.216.131")
@RestController
@RequestMapping("/image/v1")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    @PostMapping("/image/uploadImage")
    public ResponseEntity<Image> uploadProductImage(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(imageService.uploadImage(file), HttpStatus.OK);
    }

    @PutMapping("/image/update-product/{productId}/{imageId}")
    public ResponseEntity<?> updateProductWithImageId(@PathVariable("productId") String productId, @PathVariable("imageId") Integer imageId) {
        return new ResponseEntity<>(productService.updateProductWithImageId(productId, imageId), HttpStatus.OK);
    }

    @GetMapping("/image/downloadImage/{imageId}")
    public ResponseEntity<Resource> getImageById(@PathVariable("imageId") Integer imageId) throws Exception {
        try {
            Image dbFile = imageService.getImageById(imageId);
            return ResponseEntity.ok().headers(this.headers(dbFile.getFileName()))
                    .contentLength(dbFile.getData().length)
                    .contentType(MediaType
                            .parseMediaType("application/octet-stream"))
                    .body(new ByteArrayResource(dbFile.getData()));
        } catch (Exception e) {
            throw new Exception("Error downloading file");
        }
    }
    @GetMapping("/image/{imageId}")
    public ResponseEntity<?> getAllImage(@PathVariable("imageId") Integer imageId) throws Exception {
        try {
            return new ResponseEntity<>(imageService.findImageById(imageId), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Error downloading file");
        }
    }
    @DeleteMapping("/image/{imageId}")
    public ResponseEntity<?> deleteImageById(@PathVariable("imageId") Integer imageId) throws Exception {
        try {
            imageService.deleteImageById(imageId);
            return new ResponseEntity<>("Deleted Image successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Error downloading file");
        }
    }
    @GetMapping("/image/images")
    public ResponseEntity<?> getAllImage() throws Exception {
        try {
            return new ResponseEntity<>(imageService.getAllImage(), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Error downloading file");
        }
    }

    private HttpHeaders headers(String name) {

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + name);
        header.add("Cache-Control",
                "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;

    }
}
