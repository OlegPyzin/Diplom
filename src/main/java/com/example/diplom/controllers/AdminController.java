package com.example.diplom.controllers;

import com.example.diplom.model.dto.request.WorkOnWayInfoRequest;
import com.example.diplom.model.dto.response.PaymentInfoResponse;
import com.example.diplom.model.dto.response.WorkOnWayInfoResponse;
import com.example.diplom.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.diplom.constants.Constants.ADMIN_API;

@Tag(name = "Административная работа.")
@RestController
@RequestMapping(ADMIN_API)
@RequiredArgsConstructor

public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @Operation(summary = "Сформировать маршрут на рабочий день.")
    public WorkOnWayInfoResponse addWorkOnWay(@RequestBody WorkOnWayInfoRequest request) {
        return adminService.addWorkOnWay(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Посмотреть данные на сформированный маршрут")
    public WorkOnWayInfoResponse getWorkOnWay(@PathVariable Long id) {
        return adminService.getWorkOnWay(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Весь список сформированных маршрутов")
    public List<WorkOnWayInfoResponse> getAllWorkOnWay() {
        return adminService.getAllWorkOnWay();
    }

    @GetMapping("/all/payment")
    @Operation(summary = "Весь список произведенных оплат")
    public List<PaymentInfoResponse> getAllPayments() {
        return adminService.getAllPayments();
    }
}
