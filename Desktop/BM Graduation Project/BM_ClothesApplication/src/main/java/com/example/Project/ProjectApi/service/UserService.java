package com.example.Project.ProjectApi.service;


import java.util.regex.Pattern;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Project.ProjectApi.classes.EmailValidation;
import com.example.Project.ProjectApi.classes.Login;
import com.example.Project.ProjectApi.classes.Register;
import com.example.Project.ProjectApi.classes.sendUser;
import com.example.Project.ProjectApi.entity.Product;
import com.example.Project.ProjectApi.entity.SelectedItems;
import com.example.Project.ProjectApi.entity.User;
import com.example.Project.ProjectApi.repository.SelectedItemsRepository;
import com.example.Project.ProjectApi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	SelectedItemsRepository selectedItemsRepository;
	
	
	public boolean testUsingSimpleRegex(String email) {
		    String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		    return EmailValidation.patternMatches(email, regexPattern);
		}
	
	
	// login
	public sendUser login(Login login) {
		sendUser senduser= new sendUser();
		User user= userRepository.findByEmail(login.eMail);
		if(user==null) {
			 throw new RuntimeException("User does not exist.");
		}
		else {
			if(!user.password.equals(login.password))
				throw new RuntimeException("Password or E-Mail mismatch.");
			else {
				senduser.e_Mail=user.email;
				senduser.f_name=user.f_name;
				senduser.l_Name=user.l_Name;
				senduser.user_Id=user.userId;
			}
		}
		return senduser;
	}
	
	
	// register 
	public void register(Register register) {
		if(!testUsingSimpleRegex(register.e_Mail))
			throw new RuntimeException("Invalid E-Mail formula.");
		else {
		User user= userRepository.findByEmail(register.e_Mail);
		if(user!=null) {
			 throw new RuntimeException("User is already exist.");
		}
		else {
			User addUser= new User();
		addUser.email=register.e_Mail;
		addUser.f_name=register.f_name;
		addUser.l_Name=register.l_name;
		addUser.password=register.password;
        userRepository.save(addUser);
		}
	  }
	}
	
	// logout
	@Transactional
	public void logout(int userId) {
		User user=userRepository.findById(userId).get();
		selectedItemsRepository.deleteBySelectedUserId(user);
		
	}


	// profile of the user
	public User profile(int userId) {
		return userRepository.findById(userId).get();
	}



}
