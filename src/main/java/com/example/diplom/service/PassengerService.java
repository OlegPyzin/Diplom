package com.example.diplom.service;

import com.example.diplom.model.dto.request.PassengerInfoRequest;
import com.example.diplom.model.dto.response.PassengerInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class PassengerService {
    public PassengerInfoResponse addPassenger(PassengerInfoRequest request) {
        return new PassengerInfoResponse();
    }

    public PassengerInfoResponse getPassenger(Long id) {
        return new PassengerInfoResponse();
    }

    public PassengerInfoResponse updatePassenger(Long id, PassengerInfoRequest request) {
        return new PassengerInfoResponse();
    }

    public void deletePassenger(Long id) {
    }

    public List<PassengerInfoResponse> getAllPassengers() {
        return Collections.singletonList(new PassengerInfoResponse());
    }
}
