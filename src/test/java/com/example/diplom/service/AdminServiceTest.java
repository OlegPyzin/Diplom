package com.example.diplom.service;

import com.example.diplom.model.db.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private PaymentRepository paymentRepository;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void addWorkOnWay() {
    }

    @Test
    public void getWorkOnWay() {
    }

    @Test
    public void getAllWorkOnWay() {
    }

    @Test
    public void getAllPayments() {
    }
}