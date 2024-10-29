package com.example.diplom.model.db.repository;

import com.example.diplom.model.db.entity.Passenger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByNic(String nic);

    Optional<Passenger> findByEmail(String email);
}
