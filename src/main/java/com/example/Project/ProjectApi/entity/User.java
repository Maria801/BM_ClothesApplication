package com.example.Project.ProjectApi.entity;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue
	public int userId;
	public String name;
	public String password;
	public String email;
	
   /* @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "selectedUserId")
	 public List<SelectedItems> selectedItems;*/



}
