package com.example.product.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String company;
    private Date manufacturingDate;
    private Date expiryDate;
    private Integer imageId;


   /* @ManyToMany(mappedBy = "orders")
    private List<User> users;*/

}
