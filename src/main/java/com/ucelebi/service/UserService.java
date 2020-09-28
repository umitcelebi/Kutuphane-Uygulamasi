package com.ucelebi.service;

import java.util.Optional;
import com.ucelebi.models.User;

public interface UserService {
	boolean signup(User user);
	Optional<User> findByUsername(String username);
}
