package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.model.ProductResponse;

import java.util.List;

public interface ProductService {
    Product createProduct(Product productRequest);

    Product updateProduct(Product productRequest, String orderId);

    ProductResponse getProductById(Integer productId);

    List<ProductResponse> getAllProducts();

    void deleteProductById(Integer productId);

    Integer updateProductWithImageId(String productId, Integer imageId);

}
