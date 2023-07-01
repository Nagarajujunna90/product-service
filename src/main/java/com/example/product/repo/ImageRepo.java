package com.example.product.repo;

import com.example.product.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image,Integer> {
}
