package com.Inventory.service;

import java.util.List;

import com.Inventory.entity.RegisterEntity;

import jakarta.servlet.http.HttpSession;

public interface RegisterService {

	Integer saveUser(RegisterEntity entity);

	String loginUser(String email, String password, HttpSession session);
	
	

	boolean isCodeAvailable(String useremail);

	boolean checkUser(String email);

	String forgotPassword(String userEmail, String userPassword);

	List<RegisterEntity> data();

	RegisterEntity findByEmail(String userEmail);


	


	
	
	


	
	

}
