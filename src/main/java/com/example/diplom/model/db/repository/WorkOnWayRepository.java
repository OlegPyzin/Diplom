package com.example.diplom.model.db.repository;

import com.example.diplom.model.db.entity.WorkOnWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface WorkOnWayRepository extends JpaRepository<WorkOnWay, Long> {

}