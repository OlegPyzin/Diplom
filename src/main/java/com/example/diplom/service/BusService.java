package com.example.diplom.service;

import com.example.diplom.model.dto.request.BusInfoRequest;
import com.example.diplom.model.dto.response.BusInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class BusService {

    public BusInfoResponse createBus(BusInfoRequest request) {
        return new BusInfoResponse();
    }

    public BusInfoResponse getBus(Long id) {
        return new BusInfoResponse();
    }

    public BusInfoResponse updateBus(Long id, BusInfoRequest request) {
        return new BusInfoResponse();
    }

    public void deleteBus(Long id) {
    }

    public List<BusInfoResponse> getAllBuses() {
        return Collections.singletonList(new BusInfoResponse());
    }
}
