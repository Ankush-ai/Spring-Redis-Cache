package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Product;
import com.example.demo.Services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@GetMapping("/test")
	public String testMethod() {
		return "Test mehtod sucessfull";
	}

	@Autowired
	private ProductService prodService;

	@GetMapping("/getProduct/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Optional<Product> product = prodService.getProductById(id);
		return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/getAll")
	public List<Product> getAllProducts() {
		return prodService.getAllProducts();
	}

	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return prodService.saveProduct(product);
	}

	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		Optional<Product> existsProduct = prodService.getProductById(id);
		if (existsProduct.isPresent()) {
			product.setId(id);

			return ResponseEntity.ok(prodService.saveProduct(product));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delte/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
		Optional<Product> product = prodService.getProductById(id);
		if (product.isPresent()) {
			prodService.deleteProduct(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
