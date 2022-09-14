package com.example.Project.ProjectApi.classes;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnProduct {

	    public int productId;
	    public String name;
	    public int price;
	    public String description;
	    public boolean isAddedToCart;
	    public String category;
	    public int numberOfboughtItems;
	    public List<SizeColorAvailableImage>sizeColorAvailableImage=new ArrayList<SizeColorAvailableImage>();
}
