package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.model.ProductResponse;
import com.example.product.service.ImageService;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://3.88.216.131")
@RestController
@RequestMapping("/product/v1")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;


    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product productRequest) {
        Product product = productService.createProduct(productRequest);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product productRequest, @PathVariable("productId") String productId) {
        Product product = productService.updateProduct(productRequest, productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Integer productId) {
        ProductResponse product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productList = productService.getAllProducts();

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable("productId") Integer productId) {
        ProductResponse product = productService.getProductById(productId);
        if (Optional.ofNullable(product).isPresent())
            if(product.getImageId()!=null)
            imageService.deleteImageById(product.getImageId());
        productService.deleteProductById(productId);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}
