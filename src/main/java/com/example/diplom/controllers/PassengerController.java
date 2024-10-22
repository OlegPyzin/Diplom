package com.example.diplom.controllers;

import com.example.diplom.model.dto.request.PassengerInfoRequest;
import com.example.diplom.model.dto.response.PassengerInfoResponse;
import com.example.diplom.service.PassengerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static com.example.diplom.constants.Constants.PASSENGERS_API;

@Tag(name = "Пассажиры маршрутного такси")
@RestController
@RequestMapping(PASSENGERS_API)
@RequiredArgsConstructor

public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping
    @Operation(summary = "Регистрация/саморегистрация пассажира")
    public PassengerInfoResponse addPassenger(@RequestBody PassengerInfoRequest request) {
        return passengerService.addPassenger(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Информация о зарегистрированном пассажире")
    public PassengerInfoResponse getPassenger(@PathVariable Long id) {
        return passengerService.getPassenger(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных пассажира")
    public PassengerInfoResponse updatePassenger(@PathVariable Long id, @RequestBody PassengerInfoRequest request) {
        return passengerService.updatePassenger(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление/самоудаление пассажира")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Список всех зарегистрированных пассажиров")
    public List<PassengerInfoResponse> getAllPassengers() {
        return passengerService.getAllPassengers();
    }
}
