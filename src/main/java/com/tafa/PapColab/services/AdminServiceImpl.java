package com.tafa.PapColab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tafa.PapColab.controllers.dto.AdminDto;
import com.tafa.PapColab.models.Admin; // Import your Admin model
import com.tafa.PapColab.repository.AdminRepository; // Import your AdminRepository

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Lazy
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // @Override
    // public Admin save(AdminDto registrationDto) {
    // Admin admin = new Admin(
    // registrationDto.getName(),
    // registrationDto.getEmail(),
    // passwordEncoder.encode(registrationDto.getPassword()));

    // return adminRepository.save(admin);
    // }

    // @Override
    // public UserDetails loadUserByUsername(String username) throws
    // UsernameNotFoundException {
    // Admin admin = adminRepository.findByEmail(username);
    // if (admin == null) {
    // throw new UsernameNotFoundException("Invalid username or password.");
    // }
    // return new org.springframework.security.core.userdetails.User(
    // admin.getEmail(),
    // admin.getPassword(),
    // mapRolesToAuthorities(admin.getRoles()));
    // }

    @Override
    public Admin getAdminByEmail(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return admin;
    }

    @Override
    public Admin save(AdminDto registrationDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean authenticate(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }
}
