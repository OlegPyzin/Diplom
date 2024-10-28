package com.example.diplom.controllers;

import com.example.diplom.model.dto.request.DriverInfoRequest;
import com.example.diplom.model.dto.request.MedicalInfoRequest;
import com.example.diplom.model.dto.request.TechInfoRequest;
import com.example.diplom.model.dto.response.DriverInfoResponse;
import com.example.diplom.model.dto.response.MedicalInfoResponse;
import com.example.diplom.model.dto.response.TechInfoResponse;
import com.example.diplom.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.diplom.constants.Constants.DRIVERS_API;

@Tag(name = "Работа с водителями")
@RestController
@RequestMapping(DRIVERS_API)
@RequiredArgsConstructor

public class DriverController {

    private final DriverService driverService;

    @PostMapping
    @Operation(summary = "Прием на работу водителя")
    public DriverInfoResponse addDriver(@RequestBody DriverInfoRequest request) {
        return driverService.addDriver(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Посмотреть данные о водителе")
    public DriverInfoResponse getDriver(@PathVariable Long id) {
        return driverService.getDriver(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменить данные о водителе")
    public DriverInfoResponse updateDriver(@PathVariable Long id, @RequestBody DriverInfoRequest request) {
        return driverService.updateDriver(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Увольнение водителя")
    public void firedDriver(@PathVariable Long id) {
        driverService.firedDriver(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Весь список водителей")
    public List<DriverInfoResponse> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    // Медицинский осмотр водителя перед выходом на линию
    // --------------------------------------------------
    @PostMapping("/{id}")
    @Operation(summary = "Добавить данные о медицинском осмотре водителя.")
    public MedicalInfoResponse addMedical(@PathVariable Long id, @RequestBody MedicalInfoRequest request) {
        return driverService.addMedical(id, request);
    }

    @GetMapping("/{id}/medical")
    @Operation(summary = "Посмотреть данные медицинского осмотра водителя.")
    public List<MedicalInfoResponse> getMedical(@PathVariable Long id) {
        return driverService.getMedical(id);
    }

}
