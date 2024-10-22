package com.example.diplom.service;

import com.example.diplom.model.dto.request.WayInfoRequest;
import com.example.diplom.model.dto.response.WayInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class WayService {

    public WayInfoResponse addWay(WayInfoRequest request) {
        return new WayInfoResponse();
    }

    public WayInfoResponse getWay(Long id) {
        return new WayInfoResponse();
    }

    public WayInfoResponse updateWay(Long id, WayInfoRequest request) {
        return new WayInfoResponse();
    }

    public void deleteWay(Long id) {
    }

    public List<WayInfoResponse> getAllWays() {
        return Collections.singletonList(new WayInfoResponse());
    }
}
