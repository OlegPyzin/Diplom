package com.example.diplom.service;

import com.example.diplom.exceptions.CustomException;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor

public class DriverService {

    private final DriverRepository driverRepository;
    private final MedicalRepository medicalRepository;

    private final ObjectMapper mapper;


    public DriverInfoResponse addDriver(DriverInfoRequest request) {
        Driver driver = mapper.convertValue(request, Driver.class);

        driver.setDateAdded(LocalDateTime.now());
        driver.setStatus(DriverStatus.ADDED);

        Driver saved = driverRepository.save(driver);

        return mapper.convertValue(saved, DriverInfoResponse.class);
    }

    public Driver getDriverFromDB(Long id) {
        Driver driver;
        Optional<Driver> someDriver = driverRepository.findById(id);
        if( someDriver.isPresent() ) {
            driver = someDriver.get();
        } else {
            driver = null;
        }
        return driver;
    }

    public boolean checkDriver(Long id) {
        Driver driver = getDriverFromDB(id);
        if( driver != null ) {
            if( driver.getStatus() != DriverStatus.FIRED ) {
                return true;
            }
        }
        return false;
    }


    public DriverInfoResponse getDriver(Long id) {
        Driver driver = getDriverFromDB(id);

        List<MedicalInfoResponse> answerMedical = List.of(new MedicalInfoResponse());
        if( driver != null ) {
            // prepare to list medical control
            answerMedical = driver.getMedicals().stream()
                    .map(medical -> mapper.convertValue(medical,MedicalInfoResponse.class))
                    .collect(Collectors.toList());
        } else {
            // prepare for exception
            throw new CustomException(String.format("Водителя с id - %d нет или он уволен.", id), HttpStatus.BAD_REQUEST);
            //driver = new Driver();
        }
        DriverInfoResponse answer = mapper.convertValue(driver, DriverInfoResponse.class);
        answer.setMedicalInfoResponseList(answerMedical);
        return answer;
    }

    public DriverInfoResponse updateDriver(Long id, DriverInfoRequest request) {
        Driver driver = getDriverFromDB(id);
        Driver saved = new Driver();

        if(driver != null) {
            if(driver.getStatus() != DriverStatus.FIRED) {
                driver.setFirstName(request.getFirstName() == null ? driver.getFirstName() : request.getFirstName());
                driver.setLastName(request.getLastName() == null ? driver.getLastName() : request.getLastName());
                driver.setMiddleName(request.getMiddleName() == null ? driver.getMiddleName() : request.getMiddleName());
                driver.setDriveLicense(request.getDriveLicense() == null ? driver.getDriveLicense() : request.getDriveLicense());
                driver.setDriveLicenseValid(request.getDriveLicenseValid() == null ? driver.getDriveLicenseValid() : request.getDriveLicenseValid());
                driver.setHomeAddress(request.getHomeAddress() == null ? driver.getHomeAddress() : request.getHomeAddress());
                driver.setTelephone(request.getTelephone() == null ? driver.getTelephone() : request.getTelephone());
                driver.setDateModified(LocalDateTime.now());
                driver.setStatus(DriverStatus.UPDATED);

                saved = driverRepository.save(driver);
            } else {
                // prepare for exception
                // изменить данные уволенного водителя нельзя
                throw new CustomException(String.format("Изменить данные на уволенного водителя нельзя."), HttpStatus.BAD_REQUEST);
            }
        } else {
            // prepare for exception
            // изменить данные водителя не в штате нельзя
            throw new CustomException(String.format("Водителя с id - %d нет.", id), HttpStatus.BAD_REQUEST);
        }
        return mapper.convertValue(saved, DriverInfoResponse.class);
    }

    public void firedDriver(Long id) {
        Driver driver = getDriverFromDB(id);

        if( driver != null ) {
            if(driver.getStatus() != DriverStatus.FIRED) {
                driver.setDateFired(LocalDateTime.now());
                driver.setStatus(DriverStatus.FIRED);

                driverRepository.save(driver);
            } else {
                // prepare for exception
                // дважды уволить водителя нельзя
                throw new CustomException(String.format("Водитель уже уволен."), HttpStatus.BAD_REQUEST);
            }
        } else {
            // prepare for exception
            // водителя такого нет
            throw new CustomException(String.format("Водитель с id - %d не в штате.", id), HttpStatus.BAD_REQUEST);
        }
    }

    public List<DriverInfoResponse> getAllDrivers() {
//        return Collections.singletonList(new DriverInfoResponse());
        return driverRepository.findAll().stream()
                .map(driver -> mapper.convertValue(driver, DriverInfoResponse.class))
                .collect(Collectors.toList());
    }

    // Медицинский осмотр водителя перед выходом на линию
    // --------------------------------------------------
    public MedicalInfoResponse addMedical(Long id, MedicalInfoRequest request) {
        Driver driver = getDriverFromDB(id);

        if(driver != null) {
            Medical medical = new Medical();
            Medical saved;
            medical.setDriver(driver);
            medical.setWhoMadeMedical(request.getWhoMadeMedical());
            medical.setStatus(request.getStatus());
            medical.setDescriptionStatus(request.getStatus() == MedicalStatus.FAILED ? request.getDescriptionStatus() : "SUCCESS");
            medical.setDateExamination(LocalDateTime.now());

            saved = medicalRepository.save(medical);
            return mapper.convertValue(saved, MedicalInfoResponse.class);
        } else {
            // prepare for exception
            throw new CustomException(String.format("Медосмотр. Водителя с id - %d нет.", id), HttpStatus.BAD_REQUEST);
        }
        // After added exception return statement doesn't needed
        //return null;
    }

    public List<MedicalInfoResponse> getMedical(Long id) {
        // The next line is for a testing while writing the CODE
        List<MedicalInfoResponse> answer = List.of(new MedicalInfoResponse());
        Driver driver = getDriverFromDB(id);

        if(driver != null) {
            answer = driver.getMedicals().stream()
                    .map(medical -> mapper.convertValue(medical, MedicalInfoResponse.class))
                    .collect(Collectors.toList());
        } else {
            // prepare for exception
            throw new CustomException(String.format("Медосмотры. Водителя с id - %d нет.", id), HttpStatus.BAD_REQUEST);
        }
        return answer;
    }
}
