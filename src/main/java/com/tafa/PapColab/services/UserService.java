package com.tafa.PapColab.services;

import com.tafa.PapColab.controllers.dto.UserRegDto;
import com.tafa.PapColab.models.User;

public interface UserService<T> {
	User save(UserRegDto registrationDto);

	User getUserByEmail(String email);

	User getUserById(Long id);

	User deletePostById(User user, Long valueOf);

	void requestCollaboration(User user, User researcher);

	void cancelCollaboration(User user, User researcher);

	void acceptCollaboration(User user, User researcher);

	void rejectCollaboration(User user, User researcher);

	void disconnect(User user, User researcher);
}