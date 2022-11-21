package com.lakesidehotel.app.manager.repository;

import com.lakesidehotel.app.manager.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByEmail(String email);

    boolean existsByEmail(String email);
}
