package com.nw.spring.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nw.spring.exception.EmailExistException;
import com.nw.spring.form.UserForm;
import com.nw.spring.models.User;
import com.nw.spring.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public User save(UserForm userForm) throws EmailExistException {
		// TODO Auto-generated method stub
		User exitUser = userRepository.findByEmail(userForm.getEmail());
		if (exitUser != null) {
			throw new EmailExistException("The email address " + userForm.getEmail() + " is already exists in the system.");
		}
		User newUser = new User(userForm.getFullName(), userForm.getEmail(), passwordEncoder.encode(userForm.getPassword()));
		userRepository.save(newUser);
		return newUser;
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

}
