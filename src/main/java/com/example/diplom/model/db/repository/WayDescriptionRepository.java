package com.example.diplom.model.db.repository;

import com.example.diplom.model.db.entity.DescriptionWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface WayDescriptionRepository extends JpaRepository<DescriptionWay, Long> {
}
