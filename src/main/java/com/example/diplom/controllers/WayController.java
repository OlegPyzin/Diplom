package com.example.diplom.controllers;

import com.example.diplom.model.dto.request.WayInfoRequest;
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
}
