package com.example.diplom.service;

import com.example.diplom.exceptions.CustomException;
import com.example.diplom.model.db.entity.Bus;
import com.example.diplom.model.db.entity.Driver;
import com.example.diplom.model.db.entity.Medical;
import com.example.diplom.model.db.repository.DriverRepository;
import com.example.diplom.model.db.repository.MedicalRepository;
import com.example.diplom.model.dto.request.DriverInfoRequest;
import com.example.diplom.model.dto.request.MedicalInfoRequest;
import com.example.diplom.model.dto.response.DriverInfoResponse;
import com.example.diplom.model.dto.response.MedicalInfoResponse;
import com.example.diplom.model.enums.DriverStatus;
import com.example.diplom.model.enums.MedicalStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceTest {
    @InjectMocks
    public DriverService driverService;

    @Mock
    private DriverRepository driverRepository;
    @Mock
    private MedicalRepository medicalRepository;

    @Spy
    private ObjectMapper mapper;

    @Test
    public void addDriver() {
        DriverInfoRequest driverInfoRequest = new DriverInfoRequest();
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.ADDED);

        when(driverRepository.save(any(Driver.class))).thenReturn(driver);
        DriverInfoResponse answer = driverService.addDriver(driverInfoRequest);

        assertEquals(driver.getId(), answer.getId());
    }

    @Test
    public void checkDriver() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.ADDED);

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));
        Boolean value = driverService.checkDriver(driver.getId());
        assertEquals( value, true);
   }

    @Test
    public void checkDriver_ifDriverNotExist() {
        Driver driver = new Driver();
        driver.setId(1L);

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.ofNullable(null));
        driverService.checkDriver(driver.getId());
    }

    @Test
    public void checkDriver_ifDriverFired() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.FIRED);

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));
        Boolean value = driverService.checkDriver(driver.getId());
        assertEquals( value, false);
    }

    @Test
    public void getDriver() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));
        DriverInfoResponse answer = driverService.getDriver(driver.getId());

        assertEquals(driver.getId(), answer.getId());
    }

    @Test(expected = CustomException.class)
    public void getDriver_driverNotExist() {
        Driver driver = new Driver();
        driver.setId(1L);

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.ofNullable(null));
        driverService.getDriver(driver.getId());
    }

    @Test
    public void updateDriver() {
        DriverInfoRequest infoRequest = new DriverInfoRequest();
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.UPDATED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverInfoResponse answer = driverService.updateDriver(driver.getId(), infoRequest);

        assertEquals(driver.getStatus(), answer.getStatus());
    }

    @Test
    public void updateDriverByLastName() {
        DriverInfoRequest infoRequest = new DriverInfoRequest();
        infoRequest.setLastName("Test");
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.UPDATED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverInfoResponse answer = driverService.updateDriver(driver.getId(), infoRequest);

        assertEquals(driver.getStatus(), answer.getStatus());
    }

    @Test
    public void updateDriverByFirstName() {
        DriverInfoRequest infoRequest = new DriverInfoRequest();
        infoRequest.setFirstName("Test");
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.UPDATED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverInfoResponse answer = driverService.updateDriver(driver.getId(), infoRequest);

        assertEquals(driver.getStatus(), answer.getStatus());
    }

    @Test
    public void updateDriverByMiddleName() {
        DriverInfoRequest infoRequest = new DriverInfoRequest();
        infoRequest.setMiddleName("Test");
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.UPDATED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverInfoResponse answer = driverService.updateDriver(driver.getId(), infoRequest);

        assertEquals(driver.getStatus(), answer.getStatus());
    }

    @Test
    public void updateDriverByDriveLicense() {
        DriverInfoRequest infoRequest = new DriverInfoRequest();
        infoRequest.setDriveLicense("Test");
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.UPDATED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverInfoResponse answer = driverService.updateDriver(driver.getId(), infoRequest);

        assertEquals(driver.getStatus(), answer.getStatus());
    }

    @Test
    public void updateDriverByDriveLicenseValid() {
        DriverInfoRequest infoRequest = new DriverInfoRequest();
        infoRequest.setDriveLicenseValid(new Date());
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.UPDATED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverInfoResponse answer = driverService.updateDriver(driver.getId(), infoRequest);

        assertEquals(driver.getStatus(), answer.getStatus());
    }

    @Test
    public void updateDriverByHomeAddress() {
        DriverInfoRequest infoRequest = new DriverInfoRequest();
        infoRequest.setHomeAddress("Test");
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.UPDATED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverInfoResponse answer = driverService.updateDriver(driver.getId(), infoRequest);

        assertEquals(driver.getStatus(), answer.getStatus());
    }

    @Test
    public void updateDriverByTelephone() {
        DriverInfoRequest infoRequest = new DriverInfoRequest();
        infoRequest.setTelephone("Test");
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.UPDATED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverInfoResponse answer = driverService.updateDriver(driver.getId(), infoRequest);

        assertEquals(driver.getStatus(), answer.getStatus());
    }

    @Test(expected = CustomException.class)
    public void updateDriver_ifFired() {
        DriverInfoRequest infoRequest = new DriverInfoRequest();
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.FIRED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));

        driverService.updateDriver(driver.getId(), infoRequest);
    }

    @Test(expected = CustomException.class)
    public void updateDriver_ifNotExist() {
        DriverInfoRequest infoRequest = new DriverInfoRequest();
        Driver driver = new Driver();
        driver.setId(1L);

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.ofNullable(null));

        driverService.updateDriver(driver.getId(), infoRequest);
    }

    @Test
    public void firedDriver() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.ADDED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));

        driverService.firedDriver(driver.getId());

        verify(driverRepository, times(1)).save(any(Driver.class));
        assertEquals(DriverStatus.FIRED, driver.getStatus());
    }

    @Test(expected = CustomException.class)
    public void firedDriver_ifAlreadyFired() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.FIRED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));

        driverService.firedDriver(driver.getId());
    }

    @Test(expected = CustomException.class)
    public void firedDriver_ifNotExist() {
        Driver driver = new Driver();

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.ofNullable(null));

        driverService.firedDriver(driver.getId());
    }

    @Test
    public void getAllDrivers() {
        List<Driver> drivers = List.of( new Driver(), new Driver(), new Driver());

        when(driverRepository.findAll()).thenReturn(drivers);
        driverService.getAllDrivers();
    }

    @Test
    public void addMedical() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.UPDATED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));

        Medical medical = new Medical();
        medical.setWhoMadeMedical("Test test.");

        when(medicalRepository.save(any(Medical.class))).thenReturn(medical);
        MedicalInfoResponse answer = driverService.addMedical(driver.getId(), new MedicalInfoRequest());

        assertEquals( medical.getWhoMadeMedical(), answer.getWhoMadeMedical() );
    }

    @Test
    public void addMedical_ifStatusFailed() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.UPDATED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));

        MedicalInfoRequest request = new MedicalInfoRequest();
        request.setStatus(MedicalStatus.FAILED);
        Medical medical = new Medical();
        medical.setWhoMadeMedical("Test test.");

        when(medicalRepository.save(any(Medical.class))).thenReturn(medical);
        MedicalInfoResponse answer = driverService.addMedical(driver.getId(), request);

        assertEquals( medical.getStatus(), answer.getStatus() );
    }

    @Test(expected = CustomException.class)
    public void addMedical_ifDriverNotExist() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setStatus(DriverStatus.UPDATED);
        driver.setMedicals(new ArrayList<>());

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.ofNullable(null));

        driverService.addMedical(driver.getId(), new MedicalInfoRequest());
    }

    @Test
    public void getMedical() {
        Driver driver = new Driver();
        driver.setId(1L);
        List<Medical> medicals = List.of(new Medical(), new Medical());
        driver.setMedicals(medicals);

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.of(driver));

        driverService.getMedical(driver.getId());
    }

    @Test(expected = CustomException.class)
    public void getMedical_ifDriverNotExist() {
        Driver driver = new Driver();
        driver.setId(1L);

        when(driverRepository.findById(driver.getId())).thenReturn(Optional.ofNullable(null));

        driverService.getMedical(driver.getId());
    }
}