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
	public int productId;
    public String name;
    public int price;
    public String description;
    public boolean isAddedToCart;
    public String category;
    public int numberOfboughtItems;
	public String image;
    public String size;
    public String color;
    public int AvailableItems;
    public int quantity;
}

