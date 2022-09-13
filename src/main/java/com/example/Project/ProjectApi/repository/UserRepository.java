package com.example.Project.ProjectApi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Project.ProjectApi.entity.User;


public interface UserRepository extends JpaRepository<User,Integer> {
	
	public User findByEmail(String email);
	public User findByUserId(int userId);


	
}
