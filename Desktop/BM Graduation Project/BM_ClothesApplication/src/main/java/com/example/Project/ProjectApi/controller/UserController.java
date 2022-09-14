package com.example.Project.ProjectApi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Project.ProjectApi.classes.Login;
import com.example.Project.ProjectApi.classes.Register;
import com.example.Project.ProjectApi.classes.sendUser;
import com.example.Project.ProjectApi.entity.User;
import com.example.Project.ProjectApi.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@PostMapping(value= {"/login"})
	public sendUser login(@RequestBody Login login) {
		
		return userService.login(login); 
	}
	
	@PostMapping(value= {"/register"})
	public void register(@RequestBody Register register) {
		userService.register(register);
	}
	
	@DeleteMapping(value= {"/logout/{userId}"})
	public void logout(@PathVariable int userId) {
		userService.logout(userId);
	}

    @GetMapping(value= {"/profile/{userId}"})
    public User profile(@PathVariable int userId) {
    	return userService.profile(userId);
    }
    
   
}
