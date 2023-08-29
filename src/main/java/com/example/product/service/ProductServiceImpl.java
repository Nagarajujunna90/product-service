package com.example.product.service;

import com.example.product.model.Image;
import com.example.product.model.Product;
import com.example.product.model.ProductResponse;
import com.example.product.repo.ImageRepo;
import com.example.product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

  //  EventServiceLog eventServiceLog;

    @Autowired(required = false)
    KafkaTemplate kafkaTemplate;

    @Autowired
    private ImageRepo imageRepo;

    @Value("${spring.kafka.topics.create-product}")
    String createProduct;

    @Value("${spring.kafka.topics.update-product}")
    String updateProduct;

    @Value("${spring.kafka.topics.delete-product}")
    String deleteProduct;

    @Value("${spring.kafka.topics.get-product-by-id}")
    String getProductById;

    @Value("${spring.kafka.topics.get-all-products}")
    String getAllProducts;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product createProduct(Product productRequest) {
        System.out.println(createProduct);
        Product product = productRepo.save(productRequest);
        //kafkaTemplate.send(createProduct,product);
      //  eventServiceLog.addEvent(product, "PRODUCT_CREATED");
        return product;
    }

    @Override
    public Product updateProduct(Product productRequest, String productId) {
        productRequest.setId(Integer.valueOf(productId));
        Product product = productRepo.save(productRequest);
      //  kafkaTemplate.send(updateProduct,product);
     //   eventServiceLog.addEvent(product, "PRODUCT_UPDATED");
        return product;
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
      //  kafkaTemplate.send(getProductById,product);
      //  eventServiceLog.addEvent(orderId, "PRODUCT_DETAILED_FETCHED_BY_ID");
        return productResponse;

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepo.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        products.forEach(product -> {
            if (product.getImageId() != null) {
                Image image = imageRepo.findById(product.getImageId()).orElse(null);
                ProductResponse productResponse = new ProductResponse(product, image);
                productResponse.setImage(image.getData());
                productResponseList.add(productResponse);
            }
        });
      //  kafkaTemplate.send(getProductById,productResponseList);
        //eventServiceLog.addEvent(productResponseList, "ALL_PRODUCT_FETCHED");
        return productResponseList;
    }

    @Override
    public void deleteProductById(Integer orderId) {
        productRepo.deleteById(orderId);
       // eventServiceLog.addEvent(orderId, "PRODUCT_DELETED");
    }

    @Override
    public Integer updateProductWithImageId(String productId, Integer imageId) {
        Integer updateImageById = productRepo.updateImageById(productId, imageId);
      //  eventServiceLog.addEvent(imageId, "PRODUCT_DETAILS_UPDATED_BY_IMAGE_ID");
        return updateImageById;
    }
}
