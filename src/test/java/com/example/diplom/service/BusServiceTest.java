package com.example.diplom.service;

import com.example.diplom.exceptions.CustomException;
import com.example.diplom.model.db.entity.Bus;
import com.example.diplom.model.db.entity.TechControl;
import com.example.diplom.model.db.repository.BusRepository;
import com.example.diplom.model.db.repository.TechControlRepository;
import com.example.diplom.model.dto.request.BusInfoRequest;
import com.example.diplom.model.dto.request.TechInfoRequest;
import com.example.diplom.model.dto.response.BusInfoResponse;
import com.example.diplom.model.dto.response.TechInfoResponse;
import com.example.diplom.model.enums.BusStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class BusServiceTest {

    @InjectMocks
    public BusService busService;

    @Mock
    private BusRepository busRepository;
    @Mock
    private TechControlRepository techControlRepository;

    @Spy
    private ObjectMapper mapper;

    @Test
    public void addBus() {

        BusInfoRequest request = new BusInfoRequest();
        Bus bus = new Bus();
        bus.setId(1L);

        when(busRepository.save(any(Bus.class))).thenReturn(bus);
        BusInfoResponse answer = busService.addBus(request);

        assertEquals(bus.getId(), answer.getId());
    }

    @Test
    public void checkBus() {
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.ADDED);

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));
        Boolean value = busService.checkBus(bus.getId());
        assertEquals( value, true);
    }

    @Test
    public void checkBus_ifBusNotExist() {
        when(busRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        busService.checkBus(1L);
    }


    @Test
    public void checkBus_ifBusSold() {
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.SOLD);

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));
        Boolean value = busService.checkBus(bus.getId());
        assertEquals( value, false);
    }

    @Test
    public void getBus() {
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));
        BusInfoResponse answer = busService.getBus(bus.getId());

        assertEquals(bus.getId(), answer.getId());
    }

    @Test(expected = CustomException.class)
    public void getBus_busNotExist() {
        Bus bus = new Bus();
        bus.setId(1L);

        when(busRepository.findById(bus.getId())).thenReturn(Optional.ofNullable(null));
        busService.getBus(bus.getId());

    }

    @Test
    public void updateBus() {
        BusInfoRequest request = new BusInfoRequest();
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.UPDATED);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));

        when(busRepository.save(any(Bus.class))).thenReturn(bus);

        BusInfoResponse answer = busService.updateBus(bus.getId(), request);

        assertEquals( bus.getStatus(), answer.getStatus());
    }

    @Test
    public void updateBusByModel() {
        BusInfoRequest request = new BusInfoRequest();
        request.setModel("Test");
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.UPDATED);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));

        when(busRepository.save(any(Bus.class))).thenReturn(bus);

        BusInfoResponse answer = busService.updateBus(bus.getId(), request);

        assertEquals( bus.getStatus(), answer.getStatus());
    }

    @Test
    public void updateBusByDescription() {
        BusInfoRequest request = new BusInfoRequest();
        request.setDescription("Test");
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.UPDATED);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));

        when(busRepository.save(any(Bus.class))).thenReturn(bus);

        BusInfoResponse answer = busService.updateBus(bus.getId(), request);

        assertEquals( bus.getStatus(), answer.getStatus());
    }

    @Test
    public void updateBusByRegNumber() {
        BusInfoRequest request = new BusInfoRequest();
        request.setRegNumber("Test");
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.UPDATED);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));

        when(busRepository.save(any(Bus.class))).thenReturn(bus);

        BusInfoResponse answer = busService.updateBus(bus.getId(), request);

        assertEquals( bus.getStatus(), answer.getStatus());
    }

    @Test
    public void updateBusByCapacity() {
        BusInfoRequest request = new BusInfoRequest();
        Short capacity = 10;
        request.setCapacity(capacity);
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.UPDATED);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));

        when(busRepository.save(any(Bus.class))).thenReturn(bus);

        BusInfoResponse answer = busService.updateBus(bus.getId(), request);

        assertEquals( bus.getStatus(), answer.getStatus());
    }

    @Test(expected = CustomException.class)
    public void updateBus_ifSold() {
        BusInfoRequest request = new BusInfoRequest();
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.SOLD);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));

        busService.updateBus(bus.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void updateBus_ifNotExist() {
        BusInfoRequest request = new BusInfoRequest();
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.SOLD);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.ofNullable(null));

        busService.updateBus(bus.getId(), request);
    }

    @Test
    public void deleteBus() {
        BusInfoRequest request = new BusInfoRequest();
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.UPDATED);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));

        busService.deleteBus(bus.getId());

        verify(busRepository, times(1)).save(any(Bus.class));
        assertEquals( BusStatus.SOLD, bus.getStatus());
    }

    @Test(expected = CustomException.class)
    public void deleteBus_ifSold() {
        BusInfoRequest request = new BusInfoRequest();
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.SOLD);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));

        busService.deleteBus(bus.getId());
    }

    @Test(expected = CustomException.class)
    public void deleteBus_ifNotExist() {
        BusInfoRequest request = new BusInfoRequest();
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.ADDED);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.ofNullable(null));

        busService.deleteBus(bus.getId());
    }

    @Test
    public void getAllBuses() {
        List<Bus> buses = List.of( new Bus(), new Bus());

        when(busRepository.findAll()).thenReturn(buses);
        busService.getAllBuses();
    }

    @Test
    public void addTechControlBus() {
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.SOLD);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));

        TechControl techControl = new TechControl();
        techControl.setWhoMadeTechControl("Test");


        when(techControlRepository.save(any(TechControl.class))).thenReturn(techControl);

        TechInfoResponse answer = busService.addTechControlBus(bus.getId(), new TechInfoRequest());
        assertEquals( techControl.getWhoMadeTechControl(), answer.getWhoMadeTechControl() );
    }

    @Test(expected = CustomException.class)
    public void addTechControlBus_ifBusNotExist() {
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setStatus(BusStatus.SOLD);
        bus.setTechControl(new ArrayList<>());

        when(busRepository.findById(bus.getId())).thenReturn(Optional.ofNullable(null));

        busService.addTechControlBus(bus.getId(), new TechInfoRequest());
    }

    @Test
    public void getTechControlBus() {
        Bus bus = new Bus();
        bus.setId(1L);
        List<TechControl> techControls = List.of(new TechControl(), new TechControl(), new TechControl());
        bus.setTechControl(techControls);

        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));

        busService.getTechControlBus(bus.getId());
    }

    @Test(expected = CustomException.class)
    public void getTechControlBus_ifBusNotExist() {
        Bus bus = new Bus();
        bus.setId(1L);

        when(busRepository.findById(bus.getId())).thenReturn(Optional.ofNullable(null));

        busService.getTechControlBus(bus.getId());
    }

}