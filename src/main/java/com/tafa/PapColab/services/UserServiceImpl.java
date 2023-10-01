package com.tafa.PapColab.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tafa.PapColab.models.Role;
import com.tafa.PapColab.models.User;
import com.tafa.PapColab.repository.UserRepository;
import com.tafa.PapColab.controllers.dto.*;

@Service
public class UserServiceImpl implements UserService<User> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Lazy
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User save(UserRegDto registrationDto) {
		User user = new User(registrationDto.getName(),
				registrationDto.getPhone(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getAddress(),
				Arrays.asList(new Role("ROLE_USER")), registrationDto.getInterest());

		return userRepository.save(user);
	}

	@Override
	public User getUserByEmail(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return user;
	}

	@Override
	public User getUserById(Long id) throws UsernameNotFoundException {

		User user = userRepository.findById(id).get();
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return user;
	}

	@Override
	public User deletePostById(User user, Long id) {
		// List<Long> postlist = user.getPostlist();
		// postlist.remove(id);
		// user.setPostlist(postlist);

		return userRepository.saveAndFlush(user);
	}

	@Override
	public void requestCollaboration(User user, User researcher) {
		user.getSentRequests().add(researcher);
		researcher.getReceivedRequests().add(user);
		userRepository.saveAndFlush(user);
		userRepository.saveAndFlush(researcher);
	}

	@Override
	public void cancelCollaboration(User user, User researcher) {
		user.getSentRequests().remove(researcher);
		researcher.getReceivedRequests().remove(user);
		userRepository.saveAndFlush(user);
		userRepository.saveAndFlush(researcher);
	}

	@Override
	public void acceptCollaboration(User user, User researcher) {
		user.getCollaborations().add(researcher);
		user.getReceivedRequests().remove(researcher);
		researcher.getCollaborations().add(user);
		researcher.getSentRequests().remove(user);
		userRepository.saveAndFlush(user);
		userRepository.saveAndFlush(researcher);
	}

	@Override
	public void rejectCollaboration(User user, User researcher) {
		user.getReceivedRequests().remove(researcher);
		researcher.getSentRequests().remove(user);
		userRepository.saveAndFlush(user);
		userRepository.saveAndFlush(researcher);
	}

	@Override
	public void disconnect(User user, User researcher) {
		user.getCollaborations().remove(researcher);
		researcher.getCollaborations().remove(user);
		userRepository.saveAndFlush(user);
		userRepository.saveAndFlush(researcher);
	}

}
