package com.elksystems.rest.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elksystems.rest.user.repository.UserRepository;
import com.elksystems.rest.user.repository.model.User;
import com.elksystems.rest.user.ui.error.UserAlreadyExistException;
import com.elksystems.rest.user.ui.model.UserDto;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static final String TOKEN_INVALID = "invalidToken";
	public static final String TOKEN_EXPIRED = "expired";
	public static final String TOKEN_VALID = "valid";

	// API

	@Override
	public User registerNewUserAccount(final UserDto accountDto) {
		if (emailExist(accountDto.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
		}
		final User user = new User();

		user.setFirstName(accountDto.getFirstName());
		user.setLastName(accountDto.getLastName());
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		user.setEmail(accountDto.getEmail());
		user.setUsing2FA(accountDto.isUsing2FA());
		return repository.save(user);
	}

	private boolean emailExist(final String email) {
		return repository.findByEmail(email) != null;
	}
}