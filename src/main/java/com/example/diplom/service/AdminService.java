package com.example.diplom.service;

import com.example.diplom.exceptions.CustomException;
import com.example.diplom.model.db.entity.Bus;
import com.example.diplom.model.db.entity.Driver;
import com.example.diplom.model.db.entity.Way;
import com.example.diplom.model.db.entity.WorkOnWay;
import com.example.diplom.model.db.repository.PaymentRepository;
import com.example.diplom.model.db.repository.WorkOnWayRepository;
import com.example.diplom.model.dto.request.WorkOnWayInfoRequest;
import com.example.diplom.model.dto.response.PaymentInfoResponse;
import com.example.diplom.model.dto.response.WorkOnWayInfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class AdminService {

    private final PaymentRepository paymentRepository;
    private final WorkOnWayRepository workOnWayRepository;

    // заинжектим сервисы для проверки данных
    // о водителе, маршруте и микроавтобусе
    // при формировании графика работы
    private final BusService busService;
    private final WayService wayService;
    private final DriverService driverService;
    private final ObjectMapper mapper;

    // На одном маршруте могут работать несколько микроавтобусов
    // но один и тот же микроавтобус не может работать на двух маршрутах
    // также как и водитель не может работать на двух микроавтобусах и на двух маршрутах
    public WorkOnWayInfoResponse addWorkOnWay(WorkOnWayInfoRequest request) {
        WorkOnWay saved = null;
        if(busService.checkBus(request.getBusId())) {
            if(wayService.checkWay(request.getWayId())) {
                if(driverService.checkDriver((request.getDriverId()))) {

                    Driver driver = driverService.getDriverFromDB(request.getDriverId());
                    Bus bus = busService.getBusFromDB(request.getBusId());
                    Way way = wayService.getWayFromDB(request.getWayId());

                    WorkOnWay workOnWay = new WorkOnWay();
                    // Тесты дают ошибку при маппинге поэтому заменен на new WorkOnWay()
                    //WorkOnWay workOnWay = mapper.convertValue(request, WorkOnWay.class);
                    workOnWay.setDateWorkDay(request.getDateWorkDay() == null ? LocalDate.now() : request.getDateWorkDay());
                    workOnWay.setDriver(driver);
                    workOnWay.setBus(bus);
                    workOnWay.setWay(way);

                    saved = workOnWayRepository.save(workOnWay);
                } else {
                    // prepare exception
                    // нет такого водителя или он уволен
                    throw new CustomException(String.format("Водителя с id - %d нет или он уволен.", request.getDriverId()), HttpStatus.BAD_REQUEST);
                }
            } else {
                // prepare exception
                // нет такого маршрута
                throw new CustomException(String.format("Маршрута с id - %d нет.", request.getWayId()), HttpStatus.BAD_REQUEST);
            }
        } else {
            // prepare exception
            // нет такого микроавтобуса
            throw new CustomException(String.format("Микроавтобуса с id - %d нет или уже продан.", request.getBusId()), HttpStatus.BAD_REQUEST);
        }
        WorkOnWayInfoResponse answer;
        // all below needed while will added exception
        if( saved == null ) {
            answer = new WorkOnWayInfoResponse();
        } else {
            answer = mapper.convertValue(saved, WorkOnWayInfoResponse.class);
            answer.setDriverId(saved.getDriver().getId());
            answer.setBusId(saved.getBus().getId());
            answer.setWayId(saved.getWay().getId());
        }
        return answer;
    }

    private WorkOnWay getWorkOnWayFromDB(Long id) {
        WorkOnWay workOnWay;

        Optional<WorkOnWay> someWorkOnWay = workOnWayRepository.findById(id);
        if( someWorkOnWay.isPresent() ) {
            workOnWay = someWorkOnWay.get();
        } else {
            workOnWay = null;
        }
        return workOnWay;
    }

    public WorkOnWayInfoResponse getWorkOnWay(Long id) {
        WorkOnWay workOnWay = getWorkOnWayFromDB(id);

        WorkOnWayInfoResponse answer;
        if( workOnWay == null ) {
            // prepare for exception
            throw new CustomException(String.format("Рабочий маршрут с id - %d не составлен.", id), HttpStatus.BAD_REQUEST);
            // workOnWay = new WorkOnWay();
            // answer = mapper.convertValue(workOnWay, WorkOnWayInfoResponse.class);
        } else {
            answer = mapper.convertValue(workOnWay, WorkOnWayInfoResponse.class);
            answer.setDriverId(workOnWay.getDriver().getId());
            answer.setBusId(workOnWay.getBus().getId());
            answer.setWayId(workOnWay.getWay().getId());
        }
        return answer;
    }

    public List<WorkOnWayInfoResponse> getAllWorkOnWay() {
        return workOnWayRepository.findAll().stream()
                .map(workOnWay -> mapper.convertValue(workOnWay, WorkOnWayInfoResponse.class))
                .collect(Collectors.toList());
    }

    public List<PaymentInfoResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(payment -> mapper.convertValue(payment, PaymentInfoResponse.class))
                .collect(Collectors.toList());
    }
}
