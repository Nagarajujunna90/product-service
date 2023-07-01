package com.example.product.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data

public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String company;
    private Date manufacturingDate;
    private Date expiryDate;
    private Integer imageId;
    @Lob
    private byte[] image;

    public ProductResponse(Product product,Image image) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.company = product.getCompany();
        this.manufacturingDate = product.getManufacturingDate();
        this.expiryDate = product.getExpiryDate();
        this.imageId = product.getImageId();
        this.image = image.getData();
    }
    /* @ManyToMany(mappedBy = "orders")
    private List<User> users;*/

}
