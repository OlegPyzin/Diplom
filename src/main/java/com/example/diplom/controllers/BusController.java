package com.example.diplom.controllers;

import com.example.diplom.model.dto.request.BusInfoRequest;
import com.example.diplom.model.dto.response.BusInfoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static com.example.diplom.constants.Constants.*;

@RestController
@RequestMapping(BUS_API)

public class BusController {

    @PostMapping
    public BusInfoResponse addBus(@RequestBody BusInfoRequest request) {
        return new BusInfoResponse();
    }

    @GetMapping("/{id}")
    public BusInfoResponse getBus(@PathVariable Long id) {
        return new BusInfoResponse();
    }

    @PutMapping("/{id}")
    public BusInfoResponse updateBus(@PathVariable Long id, @RequestBody BusInfoRequest request) {
        return new BusInfoResponse();
    }

    @DeleteMapping("/{id}")
    public void soldBus(@PathVariable Long id) {

    }

    @GetMapping("/all")
    public List<BusInfoResponse> getAllBuses() {
        return Collections.singletonList(new BusInfoResponse());
    }
}
