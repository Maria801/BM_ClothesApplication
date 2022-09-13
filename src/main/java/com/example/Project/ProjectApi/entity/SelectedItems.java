package com.example.Project.ProjectApi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ClassId.class)
public class SelectedItems {
	

    @Id
    @ManyToOne
    @JoinColumn(name= "selectedUserId", referencedColumnName = "userId")
    public User selectedUserId;


    @Id
    @ManyToOne
    @JoinColumn(name= "selectedProduct", referencedColumnName = "mixId")
    public ProductDetails selectedProduct;

    public int boughtItemsCount=0;


	

	
}
