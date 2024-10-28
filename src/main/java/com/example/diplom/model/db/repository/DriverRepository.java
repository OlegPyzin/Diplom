package com.example.diplom.model.db.repository;

import com.example.diplom.model.db.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
