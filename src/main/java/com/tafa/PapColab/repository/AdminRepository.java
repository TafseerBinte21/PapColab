package com.tafa.PapColab.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.tafa.PapColab.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
