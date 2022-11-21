package com.lakesidehotel.app.receptionist.repository;

import com.example.lakesidehotel.app.receptionist.models.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {
    Optional<Receptionist> findByEmail(String email);

    boolean existsByEmail(String email);
}
