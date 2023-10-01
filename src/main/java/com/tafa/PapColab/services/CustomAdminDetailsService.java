package com.tafa.PapColab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tafa.PapColab.config.CustomAdminDetails;
import com.tafa.PapColab.models.Admin;
import com.tafa.PapColab.repository.AdminRepository;

public class CustomAdminDetailsService implements UserDetailsService {
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByEmail(email);
		if (admin == null) {
			throw new UsernameNotFoundException("No user found with the given email");
		}

		return new CustomAdminDetails(admin);
	}

}
