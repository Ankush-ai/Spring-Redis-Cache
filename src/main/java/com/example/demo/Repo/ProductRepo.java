package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

}
