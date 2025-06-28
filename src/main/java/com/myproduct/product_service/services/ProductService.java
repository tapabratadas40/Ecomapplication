package com.myproduct.product_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproduct.product_service.dto.ProductRequest;
import com.myproduct.product_service.entity.Product;
import com.myproduct.product_service.productrepository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getListOfProduct(){
		
		List<Product> products = productRepository.findAll();
		return products;
		
	}
	
	public void createProduct(ProductRequest product) {
		
		Product newProduct = Product.builder().
				name(product.getName()).
				price(product.getPrice()).
				description(product.getDescription()).build();

		productRepository.save(newProduct);
	}

}
