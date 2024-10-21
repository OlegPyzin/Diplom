package com.example.diplom.controllers;

import com.example.diplom.model.dto.request.DriverInfoRequest;
import com.example.diplom.model.dto.response.DriverInfoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static com.example.diplom.constants.Constants.DRIVER_API;

@RestController
@RequestMapping(DRIVER_API)
public class DriverController {

    @PostMapping
    public DriverInfoResponse addDriver(@RequestBody DriverInfoRequest request) {
        return new DriverInfoResponse();
    }

    @GetMapping("/{id}")
    public DriverInfoResponse getDriver(@PathVariable Long id) {
        return new DriverInfoResponse();
    }

    @PutMapping("/{id}")
    public DriverInfoResponse updateDriver(@PathVariable Long id, @RequestBody DriverInfoRequest request) {
        return new DriverInfoResponse();
    }

    @DeleteMapping("/{id}")
    public void firedDriver(@PathVariable Long id) {

    }

    @GetMapping("/all")
    public List<DriverInfoResponse> getAllDrivers() {
        return Collections.singletonList(new DriverInfoResponse());
    }
}
