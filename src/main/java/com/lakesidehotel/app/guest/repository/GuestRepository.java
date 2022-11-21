package com.lakesidehotel.app.guest.repository;

import com.lakesidehotel.app.guest.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findByEmail(String guestEmail);
    Optional<Guest> findById(Long id);
}

