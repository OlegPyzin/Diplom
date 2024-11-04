package com.example.diplom.service;

import com.example.diplom.exceptions.CustomException;
import com.example.diplom.model.db.entity.*;
import com.example.diplom.model.db.repository.PaymentRepository;
import com.example.diplom.model.db.repository.WorkOnWayRepository;
import com.example.diplom.model.dto.request.WorkOnWayInfoRequest;
import com.example.diplom.model.dto.response.WorkOnWayInfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private WayService wayService;

    @Mock
    private BusService busService;

    @Mock
    private DriverService driverService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private WorkOnWayRepository workOnWayRepository;


    @Spy
    private ObjectMapper mapper;

    @Test
    public void addWorkOnWay() {

        WorkOnWayInfoRequest request = new WorkOnWayInfoRequest();
        request.setWayId(1L);
        request.setBusId(1L);
        request.setDriverId(1L);
        request.setDateWorkDay(null);

        WorkOnWay workOnWay = new WorkOnWay();
        workOnWay.setId(1L);

        when(wayService.checkWay(request.getWayId())).thenReturn(true);
        when(busService.checkBus(request.getBusId())).thenReturn(true);
        when(driverService.checkDriver(request.getDriverId())).thenReturn(true);

        Driver driver = new Driver();
        driver.setId(1L);
        Bus bus = new Bus();
        bus.setId(1L);
        Way way = new Way();
        way.setId(1L);
        workOnWay.setWay(way);
        workOnWay.setDriver(driver);
        workOnWay.setBus(bus);

        when(busService.getBusFromDB(anyLong())).thenReturn(bus);
        when(wayService.getWayFromDB(anyLong())).thenReturn(way);
        when(driverService.getDriverFromDB(anyLong())).thenReturn(driver);

        when(workOnWayRepository.save(any(WorkOnWay.class))).thenReturn(workOnWay);

        WorkOnWayInfoResponse answer = adminService.addWorkOnWay(request);
        assertEquals(workOnWay.getId(), answer.getId());
    }

    @Test
    public void addWorkOnWayByDateWorkDay() {

        WorkOnWayInfoRequest request = new WorkOnWayInfoRequest();
        request.setWayId(1L);
        request.setBusId(1L);
        request.setDriverId(1L);
        request.setDateWorkDay(LocalDate.now());

        WorkOnWay workOnWay = new WorkOnWay();
        workOnWay.setId(1L);

        when(wayService.checkWay(request.getWayId())).thenReturn(true);
        when(busService.checkBus(request.getBusId())).thenReturn(true);
        when(driverService.checkDriver(request.getDriverId())).thenReturn(true);

        Driver driver = new Driver();
        driver.setId(1L);
        Bus bus = new Bus();
        bus.setId(1L);
        Way way = new Way();
        way.setId(1L);
        workOnWay.setWay(way);
        workOnWay.setDriver(driver);
        workOnWay.setBus(bus);

        when(busService.getBusFromDB(anyLong())).thenReturn(bus);
        when(wayService.getWayFromDB(anyLong())).thenReturn(way);
        when(driverService.getDriverFromDB(anyLong())).thenReturn(driver);

        when(workOnWayRepository.save(any(WorkOnWay.class))).thenReturn(workOnWay);

        WorkOnWayInfoResponse answer = adminService.addWorkOnWay(request);
        assertEquals(workOnWay.getId(), answer.getId());
    }

    @Test(expected = CustomException.class)
    public void addWorkOnWay_ifBusNotExist() {
        WorkOnWayInfoRequest request = new WorkOnWayInfoRequest();
        request.setBusId(1L);

        when(busService.checkBus(request.getBusId())).thenReturn(false);

        adminService.addWorkOnWay(request);
    }

    @Test(expected = CustomException.class)
    public void addWorkOnWay_ifWayNotExist() {
        WorkOnWayInfoRequest request = new WorkOnWayInfoRequest();
        request.setBusId(1L);
        request.setWayId(1L);


        when(busService.checkBus(request.getBusId())).thenReturn(true);
        when(wayService.checkWay(request.getWayId())).thenReturn(false);

        adminService.addWorkOnWay(request);
    }

    @Test(expected = CustomException.class)
    public void addWorkOnWay_ifDriverNotExist() {
        WorkOnWayInfoRequest request = new WorkOnWayInfoRequest();
        request.setBusId(1L);
        request.setWayId(1L);
        request.setDriverId(1L);

        when(busService.checkBus(request.getBusId())).thenReturn(true);
        when(wayService.checkWay(request.getWayId())).thenReturn(true);
        when(driverService.checkDriver(request.getDriverId())).thenReturn(false);

        adminService.addWorkOnWay(request);
    }

    @Test
    public void getWorkOnWay() {
        WorkOnWay workOnWay = new WorkOnWay();
        workOnWay.setId(1L);

        Driver driver = new Driver();
        driver.setId(1L);
        Bus bus = new Bus();
        bus.setId(1L);
        Way way = new Way();
        way.setId(1L);
        workOnWay.setWay(way);
        workOnWay.setDriver(driver);
        workOnWay.setBus(bus);

        when(workOnWayRepository.findById(workOnWay.getId())).thenReturn(Optional.of(workOnWay));

        WorkOnWayInfoResponse answer = adminService.getWorkOnWay(workOnWay.getId());
        assertEquals(workOnWay.getId(), answer.getId());
    }

    @Test(expected = CustomException.class)
    public void getWorkOnWay_ifNotExist() {
        WorkOnWay workOnWay = new WorkOnWay();
        workOnWay.setId(1L);

        when(workOnWayRepository.findById(workOnWay.getId())).thenReturn(Optional.ofNullable(null));

        adminService.getWorkOnWay(workOnWay.getId());
    }

    @Test
    public void getAllWorkOnWay() {
        List<WorkOnWay> workOnWays = List.of(new WorkOnWay(), new WorkOnWay(), new WorkOnWay());

        when(workOnWayRepository.findAll()).thenReturn(workOnWays);
        adminService.getAllWorkOnWay();
    }

    @Test
    public void getAllPayments() {
        List<Payment> payments = List.of(new Payment(), new Payment(), new Payment(), new Payment());

        when(paymentRepository.findAll()).thenReturn(payments);
        adminService.getAllPayments();
    }
}