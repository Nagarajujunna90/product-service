package com.example.product.service;

import com.example.product.model.Image;
import com.example.product.model.Product;
import com.example.product.model.ProductResponse;
import com.example.product.repo.ImageRepo;
import com.example.product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ImageRepo imageRepo;
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product createProduct(Product productRequest) {
        return productRepo.save(productRequest);
    }

    @Override
    public Product updateProduct(Product productRequest, String productId) {
        productRequest.setId(Integer.valueOf(productId));
        return productRepo.save(productRequest);

    }

    @Override
    public ProductResponse getProductById(Integer orderId) {
        Product product = productRepo.findById(orderId).orElse(null);
        ProductResponse productResponse = null;
        if (product != null && product.getImageId() != null) {
            Image image = imageRepo.findById(product.getImageId()).orElse(null);
            productResponse = new ProductResponse(product, image);
            productResponse.setImage(image != null ? image.getData() : new byte[0]);
        }
        return productResponse;

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepo.findAll();
        List<ProductResponse> productResponseList=new ArrayList<>();
        products.forEach(product -> {
            if(product.getImageId()!=null) {
                Image image = imageRepo.findById(product.getImageId()).orElse(null);
                ProductResponse  productResponse=new ProductResponse(product,image);
                productResponse.setImage(image.getData());
                productResponseList.add(productResponse);
            }
        });
        return productResponseList;
    }

    @Override
    public void deleteProductById(Integer orderId) {
         productRepo.deleteById(orderId);
    }

    @Override
    public Integer updateProductWithImageId(String productId, Integer imageId) {
        return productRepo.updateImageById(productId,imageId);
    }
}
