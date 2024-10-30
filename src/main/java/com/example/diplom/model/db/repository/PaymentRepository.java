package com.example.diplom.model.db.repository;

import com.example.diplom.model.db.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
