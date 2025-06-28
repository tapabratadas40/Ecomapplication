package com.myproduct.product_service.productrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproduct.product_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
