package com.tafa.PapColab.services;


import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tafa.PapColab.controllers.dto.AdminDto;
import com.tafa.PapColab.models.Admin;

public interface AdminService {
	Admin save(AdminDto registrationDto);

	Admin getAdminByEmail(String email) throws UsernameNotFoundException;

	boolean authenticate(String username, String password);

}