package com.example.Project.ProjectApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Project.ProjectApi.entity.Product;
import com.example.Project.ProjectApi.entity.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails,Integer>{

	public ProductDetails findBypId(Product pId);
	
	@Query("SELECT i FROM ProductDetails i WHERE i.pId=:proD")
    public ProductDetails findProductById(Product proD);
}
