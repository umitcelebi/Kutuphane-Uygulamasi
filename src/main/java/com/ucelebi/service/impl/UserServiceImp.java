package com.ucelebi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ucelebi.models.User;
import com.ucelebi.repo.UserRepository;
import com.ucelebi.service.UserService;

public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean signup(User user) {
		
		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
