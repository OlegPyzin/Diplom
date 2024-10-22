package com.example.diplom.controllers;

import com.example.diplom.model.dto.request.BusInfoRequest;
import com.example.diplom.model.dto.response.BusInfoResponse;
import com.example.diplom.service.BusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.diplom.constants.Constants.*;

@Tag(name = "Работа с микроавтобусами/автобусами")
@RestController
@RequestMapping(BUSES_API)
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;

    @PostMapping
    @Operation(summary = "Добавить микроавтобус/автобус")
    public BusInfoResponse addBus(@RequestBody BusInfoRequest request) {
        return busService.createBus(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Посмотреть данные на микроавтобус/автобус")
    public BusInfoResponse getBus(@PathVariable Long id) {
        return busService.getBus(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменить данные о микроавтобусе/автобусе")
    public BusInfoResponse updateBus(@PathVariable Long id, @RequestBody BusInfoRequest request) {
        return busService.updateBus(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Списать/продать микроавтобус/автобус")
    public void soldBus(@PathVariable Long id) {
        busService.deleteBus(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Весь список микроавтобусов/автобусов")
    public List<BusInfoResponse> getAllBuses() {
        return busService.getAllBuses();
    }
}
