package com.example.Project.ProjectApi.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class sendUser {
	public int user_Id;
	public String e_Mail;
	public String f_name;
	public String l_Name;
}
