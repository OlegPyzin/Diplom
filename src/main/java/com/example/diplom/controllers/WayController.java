package com.example.diplom.controllers;

import com.example.diplom.model.dto.request.WayDescriptionInfoRequest;
import com.example.diplom.model.dto.request.WayInfoRequest;
import com.example.diplom.model.dto.response.WayDescriptionInfoResponse;
import com.example.diplom.model.dto.response.WayInfoResponse;
import com.example.diplom.service.WayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.diplom.constants.Constants.*;

@Tag(name = "Маршруты маршрутного такси")
@RestController
@RequestMapping(WAYS_API)
@RequiredArgsConstructor

public class WayController {

    private final WayService wayService;

    @PostMapping
    @Operation(summary = "Внесение маршрута")
    public WayInfoResponse addWay(@RequestBody WayInfoRequest request) {
        return wayService.addWay(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Посмотреть данные о маршруте")
    public WayInfoResponse getWay(@PathVariable Long id) {
        return wayService.getWay(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных маршрута")
    public WayInfoResponse updateWay(@PathVariable Long id, @RequestBody WayInfoRequest request) {
        return wayService.updateWay(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление маршрута")
    public void deleteWay(@PathVariable Long id) {
        wayService.deleteWay(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Список всех маршрутов")
    public List<WayInfoResponse> getAllWays() {
        return wayService.getAllWays();
    }

    // Подробное описание маршрута
    // ---------------------------
    @PostMapping("/{id}")
    @Operation(summary = "Добавить описание части маршрута.")
    public WayDescriptionInfoResponse addPartWay(@PathVariable Long id, @RequestBody WayDescriptionInfoRequest request) {
        return wayService.addPartWay(id, request);
    }

    @PutMapping("/{id}/{partNumber}")
    @Operation(summary = "Изменение данных/описания части маршрута")
    public WayDescriptionInfoResponse updatePartWay(@PathVariable Long id, @PathVariable Short partNumber, @RequestBody WayDescriptionInfoRequest request) {
        return wayService.updatePartWay(id, partNumber, request);
    }

    @DeleteMapping("//{id}/{partNumber}")
    @Operation(summary = "Удаление описания части маршрута (Внимание!!! удаление безвозвратно)")
    public void deletePartWay(@PathVariable Long id, @PathVariable Short partNumber) {
        wayService.deletePartWay(id, partNumber);
    }

    @GetMapping("/{id}/partway")
    @Operation(summary = "Подробное описание маршрута (вывод всех частей маршрута)")
    public List<WayDescriptionInfoResponse> getPartsWay(@PathVariable Long id) {
        return wayService.getPartsWay(id);
    }
}
