package com.example.diplom.service;

import com.example.diplom.exceptions.CustomException;
import com.example.diplom.model.db.entity.Passenger;
import com.example.diplom.model.db.entity.Payment;
import com.example.diplom.model.db.repository.PassengerRepository;
import com.example.diplom.model.db.repository.PaymentRepository;
import com.example.diplom.model.dto.request.PassengerInfoRequest;
import com.example.diplom.model.dto.request.PaymentInfoRequest;
import com.example.diplom.model.dto.response.PassengerInfoResponse;
import com.example.diplom.model.enums.PassengerStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PassengerServiceTest {
    @InjectMocks
    private PassengerService passengerService;

    @Mock
    private WayService wayService;

    @Mock
    private BusService busService;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Spy
    private ObjectMapper mapper;

    @Test
    public void addPassenger() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(1L);

//        when(passengerRepository.findByNic(any(String.class))).thenReturn(Optional.ofNullable(null));
//        when(passengerRepository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(null));

        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
        PassengerInfoResponse answer = passengerService.addPassenger(request);

        assertEquals(passenger.getId(), answer.getId());
    }

    @Test(expected = CustomException.class)
    public void addPassenger_ifNicExist() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        request.setNic("Test");
        Passenger passenger = new Passenger();
        passenger.setId(1L);

        when(passengerRepository.findByNic(any(String.class))).thenReturn(Optional.of(passenger));

        passengerService.addPassenger(request);
    }

    @Test(expected = CustomException.class)
    public void addPassenger_ifEmailExist() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        request.setEmail("Test@test.com");
        Passenger passenger = new Passenger();
        passenger.setId(1L);

//        when(passengerRepository.findByNic(any(String.class))).thenReturn(Optional.ofNullable(null));
        when(passengerRepository.findByEmail(any(String.class))).thenReturn(Optional.of(passenger));

        passengerService.addPassenger(request);
    }

    @Test
    public void getPassenger() {
        Passenger passenger = new Passenger();
        passenger.setId(1L);

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));

        PassengerInfoResponse answer = passengerService.getPassenger(passenger.getId());

        assertEquals(passenger.getId(), answer.getId());
    }

    @Test(expected = CustomException.class)
    public void getPassenger_ifNotExist() {
        Passenger passenger = new Passenger();
        passenger.setId(1L);

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.ofNullable(null));

        passengerService.getPassenger(passenger.getId());
    }

    @Test
    public void updatePassenger() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        request.setPassword("Test");
        passenger.setPassword("Test");

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
        PassengerInfoResponse answer = passengerService.updatePassenger(passenger.getId(), request);

        assertEquals(passenger.getId(), answer.getId());
    }

    @Test
    public void updatePassengerByNic() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        request.setNic("Test");
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        request.setPassword("Test");
        passenger.setPassword("Test");

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
        PassengerInfoResponse answer = passengerService.updatePassenger(passenger.getId(), request);

        assertEquals(passenger.getId(), answer.getId());
    }

    @Test
    public void updatePassengerByLastName() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        request.setLastName("Test");
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        request.setPassword("Test");
        passenger.setPassword("Test");

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
        PassengerInfoResponse answer = passengerService.updatePassenger(passenger.getId(), request);

        assertEquals(passenger.getId(), answer.getId());
    }

    @Test
    public void updatePassengerByFirstName() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        request.setFirstName("Test");
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        request.setPassword("Test");
        passenger.setPassword("Test");

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
        PassengerInfoResponse answer = passengerService.updatePassenger(passenger.getId(), request);

        assertEquals(passenger.getId(), answer.getId());
    }

    @Test
    public void updatePassengerByMiddleName() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        request.setMiddleName("Test");
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        request.setPassword("Test");
        passenger.setPassword("Test");

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
        PassengerInfoResponse answer = passengerService.updatePassenger(passenger.getId(), request);

        assertEquals(passenger.getId(), answer.getId());
    }

    @Test
    public void updatePassengerByEmail() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        request.setEmail("Test@test.com");
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        request.setPassword("Test");
        passenger.setPassword("Test");

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
        PassengerInfoResponse answer = passengerService.updatePassenger(passenger.getId(), request);

        assertEquals(passenger.getId(), answer.getId());
    }

    @Test(expected = CustomException.class)
    public void updatePassenger_Anonymous() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(1L);

        passengerService.updatePassenger(passenger.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void updatePassenger_ifNotExist() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(2L);

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.ofNullable(null));
        passengerService.updatePassenger(passenger.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void updatePassenger_ifNotValidPassword() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        request.setPassword("Valid");
        passenger.setPassword("NotValid");

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        passengerService.updatePassenger(passenger.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void updatePassenger_ifNicNotUnique() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        request.setNic("Test");
        request.setPassword("Test");
        passenger.setPassword("Test");


        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));

        when(passengerRepository.findByNic(any(String.class))).thenReturn(Optional.of(passenger));
        passengerService.updatePassenger(passenger.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void updatePassenger_ifEmailNotUnique() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        request.setEmail("Test@test.com");
        request.setPassword("Test");
        passenger.setPassword("Test");


        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));

        when(passengerRepository.findByEmail(any(String.class))).thenReturn(Optional.of(passenger));
        passengerService.updatePassenger(passenger.getId(), request);
    }

    @Test
    public void deletePassenger() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        request.setPassword("Test");
        passenger.setPassword("Test");
        passenger.setStatus(PassengerStatus.ADDED);

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
        passengerService.deletePassenger(passenger.getId(), request);

        verify(passengerRepository, times(1)).save(any(Passenger.class));
        assertEquals(PassengerStatus.DELETED, passenger.getStatus());
    }

    @Test(expected = CustomException.class)
    public void deletePassenger_ifAnonymous() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(1L);

        passengerService.deletePassenger(passenger.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void deletePassenger_ifDeleted() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        passenger.setStatus(PassengerStatus.DELETED);

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        passengerService.deletePassenger(passenger.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void deletePassenger_ifNotExist() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        passenger.setStatus(PassengerStatus.ADDED);

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.ofNullable(null));
        passengerService.deletePassenger(passenger.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void deletePassenger_ifNotValidPassword() {
        PassengerInfoRequest request = new PassengerInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(2L);
        passenger.setStatus(PassengerStatus.ADDED);
        request.setPassword("Valid");
        passenger.setPassword("NotValid");

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        passengerService.deletePassenger(passenger.getId(), request);
    }


    @Test
    public void getAllPassengers() {
        List<Passenger> passengers = List.of(new Passenger(), new Passenger());

        when(passengerRepository.findAll()).thenReturn(passengers);
        passengerService.getAllPassengers();
    }

    @Test
    public void donePayment() {
        PaymentInfoRequest request = new PaymentInfoRequest();
        request.setWayId(1L);
        request.setBusId(1L);
        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setStatus(PassengerStatus.ADDED);

        Payment payment = new Payment();
        payment.setId(1L);

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        when(wayService.checkWay(request.getWayId())).thenReturn(true);
        when(busService.checkBus(request.getBusId())).thenReturn(true);

        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        passengerService.donePayment(passenger.getId(), request);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test(expected = CustomException.class)
    public void donePayment_ifPassengerNotExist() {
        PaymentInfoRequest request = new PaymentInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(1L);

        when(passengerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        passengerService.donePayment(passenger.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void donePayment_ifPassengerDeleted() {
        PaymentInfoRequest request = new PaymentInfoRequest();
        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setStatus(PassengerStatus.DELETED);

        when(passengerRepository.findById(anyLong())).thenReturn(Optional.of(passenger));
        passengerService.donePayment(passenger.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void donePayment_ifWayNotExist() {
        PaymentInfoRequest request = new PaymentInfoRequest();
        request.setWayId(1L);
        request.setBusId(1L);
        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setStatus(PassengerStatus.ADDED);

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));
        when(wayService.checkWay(request.getWayId())).thenReturn(false);

        passengerService.donePayment(passenger.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void donePayment_ifBusNotExist() {
        PaymentInfoRequest request = new PaymentInfoRequest();
        request.setWayId(1L);
        request.setBusId(1L);
        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setStatus(PassengerStatus.ADDED);

        when(passengerRepository.findById(passenger.getId())).thenReturn(Optional.of(passenger));

        when(wayService.checkWay(request.getWayId())).thenReturn(true);
        when(busService.checkBus(request.getBusId())).thenReturn(false);

        passengerService.donePayment(passenger.getId(), request);
    }
}