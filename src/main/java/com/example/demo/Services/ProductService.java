package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Product;
import com.example.demo.Repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo prodRepo;
	
	@Cacheable(value="products",key="#id")
	public Optional<Product> getProductById(Long id){
		System.out.println("Fetching products from database...");
		return prodRepo.findById(id);
	}
	
	@CachePut(value="products",key="#product.id")
	public Product saveProduct(Product product) {
		return prodRepo.save(product);
	}
	
	@CacheEvict(value="products",key="#id")
	public void deleteProduct(Long id) {
		prodRepo.deleteById(id);
		
	}
	
	public List<Product> getAllProducts(){
		return prodRepo.findAll();
		
	}
}
	
	


