package com.example.Project.ProjectApi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {
	
	@Id
	@GeneratedValue
	public int mixId;
	
	
	@ManyToOne
    @JoinColumn(name= "pId", referencedColumnName = "productId")
    public Product pId;
    public String image;
    public String size;
    public String color;
    public int AvailableItems;

}
