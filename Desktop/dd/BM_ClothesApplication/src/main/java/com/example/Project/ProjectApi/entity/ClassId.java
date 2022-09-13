package com.example.Project.ProjectApi.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassId implements Serializable{
	    public int selectedUserId;
	    public int selectedProduct;



	    @Override
	    public boolean equals(Object obj) {
	        return super.equals(obj);
	    }

	    @Override
	    public int hashCode() {
	        return super.hashCode();
	    }

}
