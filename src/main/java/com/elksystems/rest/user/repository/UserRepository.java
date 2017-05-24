package com.elksystems.rest.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.elksystems.rest.user.repository.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

	@Override
	void delete(User user);

}
