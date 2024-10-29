package com.example.diplom.model.db.repository;

import com.example.diplom.model.db.entity.DescriptionWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

@Repository

public interface WayDescriptionRepository extends JpaRepository<DescriptionWay, Long> {

    @Query(nativeQuery = true, value = "select * from financial.description_ways where way_id = :wayId and part_number = :partNumber limit 1")
    Optional<DescriptionWay> findByWayIdPartNumber(@Param("wayId") Long wayId, @Param("partNumber") Short partNumber);
}
