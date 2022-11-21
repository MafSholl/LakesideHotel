package com.lakesidehotel.app.admin.repository;

import com.lakesidehotel.app.admin.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmail(String adminEmail);

    boolean existsByEmail(String email);
}
