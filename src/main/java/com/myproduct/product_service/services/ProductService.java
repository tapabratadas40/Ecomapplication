package com.myproduct.product_service.services;

import java.util.ArrayList;
import java.util.List;

import com.myproduct.product_service.dto.ProductResponce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproduct.product_service.dto.ProductRequest;
import com.myproduct.product_service.entity.Product;
import com.myproduct.product_service.productrepository.ProductRepository;

@Service
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductResponce> getListOfProduct(){
		
		List<Product> products = productRepository.findAll();
		List<ProductResponce> responceObj  = products.stream().map(e -> mapToProductResponce(e)).toList();
		return responceObj;
		
	}

	private ProductResponce mapToProductResponce(Product products) {
		return new ProductResponce().builder().name(products.getName()).id(products.getId())
				.description(products.getDescription()).price(products.getPrice()).build();
	}

	public void createProduct(ProductRequest product) {
		
		Product newProduct = Product.builder().name(product.getName()).
				price(product.getPrice()).
				description(product.getDescription()).build();
		productRepository.save(newProduct);
		log.info("Product created : "+newProduct.getId());

	}

}
