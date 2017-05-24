package com.elksystems.rest.user.service;

import com.elksystems.rest.user.repository.model.User;
import com.elksystems.rest.user.ui.error.UserAlreadyExistException;
import com.elksystems.rest.user.ui.model.UserDto;

public interface IUserService {

	User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

}
