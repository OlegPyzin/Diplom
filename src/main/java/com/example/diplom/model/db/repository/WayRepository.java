package com.example.diplom.model.db.repository;

import com.example.diplom.model.db.entity.Way;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface WayRepository extends JpaRepository<Way, Long> {
}
