package com.myproduct.product_service.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.myproduct.product_service.dto.ProductResponce;
import com.myproduct.product_service.exceptions.ProductNotFoundException;
import com.myproduct.product_service.exceptions.ProductNotSavedInDbException;
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

	public ProductResponce getProductByProductId(String productId){
		Optional<Product> byId = productRepository.findById(productId);
		byId.orElseThrow(()->new ProductNotFoundException("Product Not Found For the productId: "+productId));
		return mapToProductResponce(byId.get());
	}

	public ProductResponce createProduct(ProductRequest product) {
		
		Product newProduct = Product.builder().name(product.getName()).
				price(product.getPrice()).
				description(product.getDescription()).build();
		Optional<Product> savedProduct = Optional.of(productRepository.save(newProduct));
		savedProduct.orElseThrow(()->new ProductNotSavedInDbException("Due to Some Error Product Not Saved In Data Base"));
		log.info("Product created : "+newProduct.getId());
		return mapToProductResponce(savedProduct.get());

	}

	public ProductResponce updateProduct(String id,ProductRequest productRequest){
		Optional<Product> productDetails = Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found For the productId: " + id)));
		Product productUpdateRequest = productDetails.get().toBuilder().name(productRequest.getName()).price(productRequest.getPrice())
				.description(productRequest.getDescription()).build();
		Product savedProduct = productRepository.save(productUpdateRequest);
		return ProductResponce.builder().name(savedProduct.getName()).id(savedProduct.getId())
				.price(savedProduct.getPrice()).description(savedProduct.getDescription()).build();
	}

	public void deleteProduct(String id){
		Optional<Product> productDetails = productRepository.findById(id);
		productRepository.delete(productDetails.get());
	}
}
