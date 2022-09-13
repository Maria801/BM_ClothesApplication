package com.example.Project.ProjectApi.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
	public String eMail;
	public String password;
}
