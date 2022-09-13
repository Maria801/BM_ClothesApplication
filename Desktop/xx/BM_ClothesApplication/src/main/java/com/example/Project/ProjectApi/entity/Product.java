package com.example.Project.ProjectApi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	   @GeneratedValue
	    @Id
	    public int productId;
	    public String name;
	    public int price;
	    public String description;
	    public boolean isAddedToCart;
	    public String category;
	    public int numberOfboughtItems;
	    public int AvailableItems;


	    /*@OneToMany(fetch = FetchType.LAZY,mappedBy = "selectedProductId")
	    @JsonIgnore
	    public List<SelectedItems> selectedItems;

	   
	    @OneToMany(fetch = FetchType.LAZY,mappedBy = "pId")
	    public List<ProductDetails> productDetails;*/
	    



	
}
