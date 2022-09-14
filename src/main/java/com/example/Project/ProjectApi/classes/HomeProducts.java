package com.example.Project.ProjectApi.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeProducts {
	
    public int proId;
    public String name;
    public int price;
    public String image;
   
}
