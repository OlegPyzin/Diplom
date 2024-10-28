package com.example.diplom.service;

import com.example.diplom.model.db.entity.DescriptionWay;
import com.example.diplom.model.db.entity.Way;
import com.example.diplom.model.db.repository.WayDescriptionRepository;
import com.example.diplom.model.db.repository.WayRepository;
import com.example.diplom.model.dto.request.WayDescriptionInfoRequest;
import com.example.diplom.model.dto.request.WayInfoRequest;
import com.example.diplom.model.dto.response.WayDescriptionInfoResponse;
import com.example.diplom.model.dto.response.WayInfoResponse;
import com.example.diplom.model.enums.WayStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class WayService {

    private final WayRepository wayRepository;
    private final WayDescriptionRepository wayDescriptionRepository;
    private final ObjectMapper mapper;

    public WayInfoResponse addWay(WayInfoRequest request) {

        Way way = mapper.convertValue(request, Way.class);

        way.setDateAdded(LocalDateTime.now());
        way.setStatus(WayStatus.ADDED);

        Way saved = wayRepository.save(way);

        return mapper.convertValue(saved, WayInfoResponse.class);
    }

    private Way getWayFromDB(Long id) {
        Way way;
        Optional<Way> someWay = wayRepository.findById(id);
        if( someWay.isPresent() ) {
            way = someWay.get();
        } else {
            way = null;
        }
        return way;
    }

    public WayInfoResponse getWay(Long id) {
        Way way = getWayFromDB(id);

        List<WayDescriptionInfoResponse> answerPartsWay = List.of(new WayDescriptionInfoResponse());
        if( way != null ) {
            answerPartsWay = way.getDescriptionWays().stream()
                    .map(descriptionWay -> mapper.convertValue(descriptionWay, WayDescriptionInfoResponse.class))
                    .collect(Collectors.toList());
        } else {
            // prepare for exception
            way = new Way();
        }
        WayInfoResponse answer = mapper.convertValue(way, WayInfoResponse.class);
        answer.setWayDescriptionInfoResponseList(answerPartsWay);
        return answer;
    }

    public WayInfoResponse updateWay(Long id, WayInfoRequest request) {
        Way way = getWayFromDB(id);
        Way saved = new Way();
        if( way != null ) {
            if( way.getStatus() != WayStatus.DELETED ) {
                way.setDescription(request.getDescription() == null ? way.getDescription() : request.getDescription());
                way.setWayNumber(request.getWayNumber() == null ? way.getWayNumber() : request.getWayNumber());
                way.setCost(request.getCost() == null ? way.getCost() : request.getCost());
                way.setWayLength(request.getWayLength() == null ? way.getWayLength() : request.getWayLength());
                way.setDateModified(LocalDateTime.now());
                way.setStatus(WayStatus.UPDATED);

                saved = wayRepository.save(way);
            } else {
                // prepare for exception
                // изменить данные удаленного маршрута нельзя
            }
        } else {
            // prepare for exception
            way = new Way();
        }

        return mapper.convertValue(saved, WayInfoResponse.class);
    }

    public void deleteWay(Long id) {
        Way way = getWayFromDB(id);
        if( way != null ) {
            if( way.getStatus() != WayStatus.DELETED ) {
                way.setDateDeleted(LocalDateTime.now());
                way.setStatus(WayStatus.DELETED);

                wayRepository.save(way);
            } else {
                // prepare for exception
                // изменить данные удаленного маршрута нельзя
            }
        } else {
            // prepare for exception
            // маршрута то и нет
        }
    }

    public List<WayInfoResponse> getAllWays() {
        //return Collections.singletonList(new WayInfoResponse());
        return wayRepository.findAll().stream()
                .map(way -> mapper.convertValue(way, WayInfoResponse.class))
                .collect(Collectors.toList());
    }


    // Подробное описание маршрута
    // ---------------------------
    public WayDescriptionInfoResponse addPartWay(Long id, WayDescriptionInfoRequest request) {
        Way way = getWayFromDB(id);

        if( way != null ) {
            DescriptionWay saved;
            DescriptionWay descriptionWay = mapper.convertValue(request,DescriptionWay.class);
            descriptionWay.setWay(way);
            descriptionWay.setDateAdded(LocalDateTime.now());
            descriptionWay.setStatus(WayStatus.ADDED);

            saved = wayDescriptionRepository.save(descriptionWay);
            return mapper.convertValue(saved, WayDescriptionInfoResponse.class);
        } else {
            // prepare for exception
        }
        return null;
    }

    public List<WayDescriptionInfoResponse> getPartsWay(Long id) {
        List<WayDescriptionInfoResponse> answer = List.of(new WayDescriptionInfoResponse());
        Way way = getWayFromDB(id);

        if( way != null ) {
            answer = way.getDescriptionWays().stream()
                    .map(descriptionWay -> mapper.convertValue(descriptionWay, WayDescriptionInfoResponse.class))
                    .collect(Collectors.toList());
        } else {
            // prepare for exception
        }
        return answer;
    }

    public WayDescriptionInfoResponse updatePartWay(Long id, Short partNumber, WayDescriptionInfoRequest request) {
        return new WayDescriptionInfoResponse();
    }
}
