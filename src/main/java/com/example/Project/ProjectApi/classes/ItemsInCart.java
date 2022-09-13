package com.example.Project.ProjectApi.classes;

import java.util.List;

import com.example.Project.ProjectApi.entity.Product;
import com.example.Project.ProjectApi.entity.ProductDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsInCart {
	    public ProductDetails productDetails;
	    public int quantity;
}

