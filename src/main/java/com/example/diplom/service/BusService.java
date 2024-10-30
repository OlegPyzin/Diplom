package com.example.diplom.service;

import com.example.diplom.model.db.entity.Bus;
import com.example.diplom.model.db.entity.TechControl;
import com.example.diplom.model.db.entity.Way;
import com.example.diplom.model.db.repository.BusRepository;
import com.example.diplom.model.db.repository.TechControlRepository;
import com.example.diplom.model.dto.request.BusInfoRequest;
import com.example.diplom.model.dto.request.TechInfoRequest;
import com.example.diplom.model.dto.response.BusInfoResponse;
import com.example.diplom.model.dto.response.TechInfoResponse;
import com.example.diplom.model.enums.BusStatus;
import com.example.diplom.model.enums.WayStatus;
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

public class BusService {

    private final BusRepository busRepository;
    private final TechControlRepository techControlRepository;
    private final ObjectMapper mapper;

    public BusInfoResponse addBus(BusInfoRequest request) {
        Bus bus = mapper.convertValue(request, Bus.class);

        bus.setDateAdded(LocalDateTime.now());
        bus.setStatus(BusStatus.ADDED);
        Bus saved = busRepository.save(bus);

        return mapper.convertValue(saved, BusInfoResponse.class);
    }

    private Bus getBusFromDB(Long id) {
        Bus bus;
        Optional<Bus> someBus = busRepository.findById(id);
        if( someBus.isPresent() ) {
            bus = someBus.get();
        } else {
            bus = null;
        }

        return bus;
    }

    public boolean checkBus(Long id) {
        Bus bus = getBusFromDB(id);
        if( bus != null ) {
            if( bus.getStatus() != BusStatus.SOLD ) {
                return true;
            }
        }
        return false;
    }

    public BusInfoResponse getBus(Long id) {
        Bus bus = getBusFromDB(id);

        List<TechInfoResponse> answerTech = List.of(new TechInfoResponse());
        if( bus != null ) {
            answerTech = bus.getTechControl().stream()
                    .map(techControl -> mapper.convertValue(techControl, TechInfoResponse.class))
                    .collect(Collectors.toList());
        } else {
            // prepare for exception
            bus = new Bus();
        }

        BusInfoResponse answer = mapper.convertValue(bus, BusInfoResponse.class);
        answer.setTechInfoResponseList(answerTech);
        return answer;
    }

    public BusInfoResponse updateBus(Long id, BusInfoRequest request) {
        Bus bus = getBusFromDB(id);
        Bus saved = new Bus();

        if( bus != null ) {
            if(bus.getStatus() != BusStatus.SOLD) {

                bus.setModel(request.getModel() == null ? bus.getModel() : request.getModel());
                bus.setDescription(request.getDescription() == null ? bus.getDescription() : request.getDescription());
                bus.setRegNumber(request.getRegNumber() == null ? bus.getRegNumber() : request.getRegNumber());
                bus.setCapacity(request.getCapacity() == null ? bus.getCapacity() : request.getCapacity());
                bus.setDateModified(LocalDateTime.now());
                bus.setStatus(BusStatus.UPDATED);

                saved = busRepository.save(bus);
            } else {
                // prepare for exception
            }
        } else {
            // prepare for exception
        }
        return mapper.convertValue(saved, BusInfoResponse.class);
    }

    public void deleteBus(Long id) {
        Bus bus = getBusFromDB(id);

        if( bus != null ) {
            if(bus.getStatus() != BusStatus.SOLD) {
                bus.setDateModified(LocalDateTime.now());
                bus.setDateSold(LocalDateTime.now());
                bus.setStatus(BusStatus.SOLD);

                busRepository.save(bus);
            } else {
                // prepare for exception
            }
        } else {
            // prepare for exception
        }
    }

    public List<BusInfoResponse> getAllBuses() {
        return busRepository.findAll().stream()
                .map(bus -> mapper.convertValue(bus, BusInfoResponse.class))
                .collect(Collectors.toList());
    }

    // Технический контроль состояния автотранспорта
    // ---------------------------------------------
    public TechInfoResponse addTechControlBus(Long id, TechInfoRequest request) {
        Bus bus = getBusFromDB(id);

        if( bus != null ) {
            TechControl techControl = new TechControl();
            TechControl saved;
            techControl.setBus(bus);
            techControl.setWhoMadeTechControl(request.getWhoMadeTechControl());
            techControl.setDateTechControl(LocalDateTime.now());
            techControl.setDateNextTechControl(LocalDateTime.now().plusYears(1L));
            saved = techControlRepository.save(techControl);
            return mapper.convertValue(saved, TechInfoResponse.class);
        }
        else {
            // prepare for exception
        }
        return null;
    }

    public List<TechInfoResponse> getTechControlBus(Long id) {
        // The next line is for a testing while writing the CODE
        List<TechInfoResponse> answer = List.of(new TechInfoResponse(), new TechInfoResponse());
        Bus bus = getBusFromDB(id);
        if( bus != null ) {
            answer = bus.getTechControl().stream()
                    .map(techControl -> mapper.convertValue(techControl, TechInfoResponse.class))
                    .collect(Collectors.toList());
        } else {
            // prepare for exception
        }

        return answer;
    }
}
