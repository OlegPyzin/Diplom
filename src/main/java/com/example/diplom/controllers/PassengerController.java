package com.example.diplom.controllers;

import com.example.diplom.model.dto.request.PassengerInfoRequest;
import com.example.diplom.model.dto.response.PassengerInfoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static com.example.diplom.constants.Constants.PASSENGER_API;

@RestController
@RequestMapping(PASSENGER_API)

public class PassengerController {
    @PostMapping
    public PassengerInfoResponse addPassenger(@RequestBody PassengerInfoRequest request) {
        return new PassengerInfoResponse();
    }

    @GetMapping("/{id}")
    public PassengerInfoResponse getPassenger(@PathVariable Long id) {
        return new PassengerInfoResponse();
    }

    @PutMapping("/{id}")
    public PassengerInfoResponse updatePassenger(@PathVariable Long id, @RequestBody PassengerInfoRequest request) {
        return new PassengerInfoResponse();
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {

    }

    @GetMapping("/all")
    public List<PassengerInfoResponse> getAllPassengers() {
        return Collections.singletonList(new PassengerInfoResponse());
    }
}
