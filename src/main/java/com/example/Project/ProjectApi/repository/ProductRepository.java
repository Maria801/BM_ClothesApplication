package com.example.Project.ProjectApi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Project.ProjectApi.entity.Product;
import com.example.Project.ProjectApi.entity.ProductDetails;
import com.example.Project.ProjectApi.entity.SelectedItems;
import com.example.Project.ProjectApi.entity.User;

public interface ProductRepository extends JpaRepository<Product,Integer>{

	public Product findByProductId(int productId);
	
	
}
