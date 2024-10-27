package com.example.diplom.model.db.repository;

import com.example.diplom.model.db.entity.TechControl;
import com.example.diplom.model.dto.response.TechInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TechControlRepository extends JpaRepository<TechControl, Long> {

}
