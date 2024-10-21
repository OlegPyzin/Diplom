package com.example.diplom.controllers;

import com.example.diplom.model.dto.request.WayInfoRequest;
import com.example.diplom.model.dto.response.WayInfoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static com.example.diplom.constants.Constants.*;

@RestController
@RequestMapping(WAY_API)

public class WayController {
    @PostMapping
    public WayInfoResponse addWay(@RequestBody WayInfoRequest request) {
        return new WayInfoResponse();
    }

    @GetMapping("/{id}")
    public WayInfoResponse getWay(@PathVariable Long id) {
        return new WayInfoResponse();
    }

    @PutMapping("/{id}")
    public WayInfoResponse updateWay(@PathVariable Long id, @RequestBody WayInfoRequest request) {
        return new WayInfoResponse();
    }

    @DeleteMapping("/{id}")
    public void deleteWay(@PathVariable Long id) {

    }

    @GetMapping("/all")
    public List<WayInfoResponse> getAllWays() {
        return Collections.singletonList(new WayInfoResponse());
    }
}
