package com.example.diplom.model.db.repository;

import com.example.diplom.model.db.entity.Medical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MedicalRepository extends JpaRepository<Medical, Long> {
}
