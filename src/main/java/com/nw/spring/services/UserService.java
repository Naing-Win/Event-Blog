package com.nw.spring.services;

import com.nw.spring.exception.EmailExistException;
import com.nw.spring.form.UserForm;
import com.nw.spring.models.User;

public interface UserService {

	public User save(UserForm userForm) throws EmailExistException;
	public User findByEmail(String email);
}
