package com.example.diplom.service;

import com.example.diplom.model.dto.request.DriverInfoRequest;
import com.example.diplom.model.dto.response.DriverInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor

public class DriverService {

    public DriverInfoResponse addDriver(DriverInfoRequest request) {
        return new DriverInfoResponse();
    }

    public DriverInfoResponse getDriver(Long id) {
        return new DriverInfoResponse();
    }

    public DriverInfoResponse updateDriver(Long id, DriverInfoRequest request) {
        return new DriverInfoResponse();
    }

    public void firedDriver(Long id) {
    }

    public List<DriverInfoResponse> getAllDrivers() {
        return Collections.singletonList(new DriverInfoResponse());
    }

}
