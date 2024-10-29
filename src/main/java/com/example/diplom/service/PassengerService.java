package com.example.diplom.service;

import com.example.diplom.model.db.entity.Passenger;
import com.example.diplom.model.db.repository.PassengerRepository;
import com.example.diplom.model.dto.request.PassengerInfoRequest;
import com.example.diplom.model.dto.response.PassengerInfoResponse;
import com.example.diplom.model.enums.PassengerStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final ObjectMapper mapper;

    public PassengerInfoResponse addPassenger(PassengerInfoRequest request) {
        Passenger saved;
        // Проверка на уникальность NIC
        Optional<Passenger> uniqueValue = passengerRepository.findByNic(request.getNic());
        if( uniqueValue.isPresent() ) {
            // prepare for exception
            // nic must be unique, while return new Passenger
            saved = new Passenger();
        } else {
            // Проверка на уникальность email
            uniqueValue = passengerRepository.findByEmail(request.getEmail());
            if( uniqueValue.isPresent() ) {
                // prepare for exception
                // email must be unique, while return new Passenger
                saved = new Passenger();
            } else {
                Passenger passenger = mapper.convertValue(request, Passenger.class);
                passenger.setDateAdded(LocalDateTime.now());
                passenger.setStatus(PassengerStatus.ADDED);
                saved = passengerRepository.save(passenger);
            }
        }
        return mapper.convertValue(saved, PassengerInfoResponse.class);
    }

    private Passenger getPassengerFromDB(Long id) {
        Passenger passenger;
        Optional<Passenger> somePassenger = passengerRepository.findById(id);
        if( somePassenger.isPresent() ) {
            passenger = somePassenger.get();
        } else {
            passenger = null;
        }
        return passenger;
    }

    public PassengerInfoResponse getPassenger(Long id) {
        Passenger passenger = getPassengerFromDB(id);

        if( passenger == null ) {
            // prepare for exception
            passenger = new Passenger();
        }

        return mapper.convertValue(passenger, PassengerInfoResponse.class);
    }

    public PassengerInfoResponse updatePassenger(Long id, PassengerInfoRequest request) {
        if( id > 1 ) {
            Passenger saved;
            Passenger passenger = getPassengerFromDB(id);
            if( passenger != null ) {
                if( passenger.getPassword().equals(request.getPassword()) ) {
                    // Проверка на уникальность NIC
                    Optional<Passenger> uniqueValue = passengerRepository.findByNic(request.getNic());
                    if( uniqueValue.isPresent() ) {
                        // prepare for exception
                        // nic must be unique, while return new Passenger
                        saved = new Passenger();
                    } else {
                        // Проверка на уникальность email
                        uniqueValue = passengerRepository.findByEmail(request.getEmail());
                        if( uniqueValue.isPresent() ) {
                            // prepare for exception
                            // email must be unique, while return new Passenger
                            saved = new Passenger();
                        } else {
                            passenger.setNic(request.getNic() == null ? passenger.getNic() : request.getNic());
                            passenger.setLastName(request.getLastName() == null ? passenger.getLastName() : request.getLastName());
                            passenger.setFirstName(request.getFirstName() == null ? passenger.getFirstName() : request.getFirstName());
                            passenger.setMiddleName(request.getMiddleName() == null ? passenger.getMiddleName() : request.getMiddleName());
                            passenger.setEmail(request.getEmail() == null ? passenger.getEmail() : request.getEmail());
                            passenger.setDateModified(LocalDateTime.now());
                            passenger.setStatus(PassengerStatus.UPDATED);
                            saved = passengerRepository.save(passenger);
                        }
                    }
                    return mapper.convertValue(saved, PassengerInfoResponse.class);
                } else {
                    // prepare for exception
                    // password in database not equals
                }
            } else {
                // prepare for exception
                // passenger doesn't exist
            }
        } else {
            // prepare for exception
            // id = 1 always own anonymous passenger
            // and never will change
        }
        return new PassengerInfoResponse();
    }

    public void deletePassenger(Long id, PassengerInfoRequest request) {
        if (id > 1) {
            Passenger passenger = getPassengerFromDB(id);
            if (passenger != null) {
                if( passenger.getStatus() != PassengerStatus.DELETED) {
                    if (passenger.getPassword().equals(request.getPassword())) {
                        passenger.setDateDeleted(LocalDateTime.now());
                        passenger.setStatus(PassengerStatus.DELETED);
                        passengerRepository.save(passenger);
                    } else {
                        // prepare for exception
                        // password in database not equals
                    }
                } else {
                    // prepare for exception
                    // can not delete deleted passenger
                }
            } else {
                // prepare for exception
                // passenger doesn't exist
            }
        } else {
            // prepare for exception
            // id = 1 always own anonymous passenger
            // and never may be deleted
        }
    }

    public List<PassengerInfoResponse> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(passenger -> mapper.convertValue(passenger, PassengerInfoResponse.class))
                .collect(Collectors.toList());
    }
}
