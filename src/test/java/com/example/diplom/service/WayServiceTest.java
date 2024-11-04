package com.example.diplom.service;

import com.example.diplom.exceptions.CustomException;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WayServiceTest {

    @InjectMocks
    private WayService wayService;

    @Mock
    private WayRepository wayRepository;

    @Mock
    private WayDescriptionRepository wayDescriptionRepository;

    @Spy
    private ObjectMapper mapper;

    @Test
    public void addWay() {
        WayInfoRequest request = new WayInfoRequest();
        Way way = new Way();
        way.setId(1L);

        when(wayRepository.save(any(Way.class))).thenReturn(way);
        WayInfoResponse answer = wayService.addWay(request);

        assertEquals(way.getId(), answer.getId());
    }

    @Test
    public void checkWay() {
        Way way = new Way();
        way.setId(1L);
        way.setStatus(WayStatus.ADDED);

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));
        Boolean value = wayService.checkWay(way.getId());
        assertEquals( value, true);
    }

    @Test
    public void checkWay_ifWayNotExist() {
        Way way = new Way();
        way.setId(1L);

        when(wayRepository.findById(way.getId())).thenReturn(Optional.ofNullable(null));
        Boolean value = wayService.checkWay(way.getId());
        assertEquals( value, false);
    }

    @Test
    public void checkWay_ifWayDeleted() {
        Way way = new Way();
        way.setId(1L);
        way.setStatus(WayStatus.DELETED);

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));
        Boolean value = wayService.checkWay(way.getId());
        assertEquals( value, false);
    }


    @Test
    public void getWay() {
        Way way = new Way();
        way.setId(1L);
        way.setDescriptionWays(new ArrayList<>());

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));

        WayInfoResponse answer = wayService.getWay(way.getId());
        assertEquals(way.getId(), answer.getId());
    }

    @Test(expected = CustomException.class)
    public void getWay_ifWayNotExists() {
        Way way = new Way();
        way.setId(1L);

        when(wayRepository.findById(way.getId())).thenReturn(Optional.ofNullable(null));

        wayService.getWay(way.getId());
    }

    @Test
    public void updateWay() {
        WayInfoRequest request = new WayInfoRequest();
        Way way = new Way();
        way.setId(1L);
        way.setDescriptionWays(new ArrayList<>());

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));
        when(wayRepository.save(any(Way.class))).thenReturn(way);

        WayInfoResponse answer = wayService.updateWay(way.getId(), request);
        assertEquals( way.getStatus(), answer.getStatus());
    }

    @Test
    public void updateWayByDescription() {
        WayInfoRequest request = new WayInfoRequest();
        request.setDescription("Test of description");
        Way way = new Way();
        way.setId(1L);
        way.setDescriptionWays(new ArrayList<>());

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));
        when(wayRepository.save(any(Way.class))).thenReturn(way);

        WayInfoResponse answer = wayService.updateWay(way.getId(), request);
        assertEquals( way.getStatus(), answer.getStatus());
    }

    @Test
    public void updateWayByWayNumber() {
        WayInfoRequest request = new WayInfoRequest();
        request.setWayNumber("01");
        Way way = new Way();
        way.setId(1L);
        way.setDescriptionWays(new ArrayList<>());

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));
        when(wayRepository.save(any(Way.class))).thenReturn(way);

        WayInfoResponse answer = wayService.updateWay(way.getId(), request);
        assertEquals( way.getStatus(), answer.getStatus());
    }

    @Test
    public void updateWayByCost() {
        WayInfoRequest request = new WayInfoRequest();
        request.setCost((BigDecimal.valueOf(50L)));
        Way way = new Way();
        way.setId(1L);
        way.setDescriptionWays(new ArrayList<>());

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));
        when(wayRepository.save(any(Way.class))).thenReturn(way);

        WayInfoResponse answer = wayService.updateWay(way.getId(), request);
        assertEquals( way.getStatus(), answer.getStatus());
    }

    @Test
    public void updateWayByWayLength() {
        WayInfoRequest request = new WayInfoRequest();
        request.setWayLength(10f);
        Way way = new Way();
        way.setId(1L);
        way.setDescriptionWays(new ArrayList<>());

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));
        when(wayRepository.save(any(Way.class))).thenReturn(way);

        WayInfoResponse answer = wayService.updateWay(way.getId(), request);
        assertEquals( way.getStatus(), answer.getStatus());
    }

    @Test(expected = CustomException.class)
    public void updateWay_ifDeleted() {
        WayInfoRequest request = new WayInfoRequest();
        Way way = new Way();
        way.setId(1L);
        way.setStatus(WayStatus.DELETED);
        way.setDescriptionWays(new ArrayList<>());

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));
        wayService.updateWay(way.getId(), request);
    }

    @Test(expected = CustomException.class)
    public void updateWay_ifNotExist() {
        WayInfoRequest request = new WayInfoRequest();
        Way way = new Way();
        way.setId(1L);

        when(wayRepository.findById(way.getId())).thenReturn(Optional.ofNullable(null));
        wayService.updateWay(way.getId(), request);
    }

    @Test
    public void deleteWay() {
        Way way = new Way();
        way.setId(1L);
        way.setDescriptionWays(new ArrayList<>());

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));

        wayService.deleteWay(way.getId());

        verify(wayRepository, times(1)).save(any(Way.class));
        assertEquals( WayStatus.DELETED, way.getStatus());
    }

    @Test(expected = CustomException.class)
    public void deleteWay_ifDeleted() {
        Way way = new Way();
        way.setId(1L);
        way.setStatus(WayStatus.DELETED);
        way.setDescriptionWays(new ArrayList<>());

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));

        wayService.deleteWay(way.getId());
    }

    @Test(expected = CustomException.class)
    public void deleteWay_ifNotExist() {
        Way way = new Way();
        way.setId(1L);
        way.setStatus(WayStatus.DELETED);
        way.setDescriptionWays(new ArrayList<>());

        when(wayRepository.findById(way.getId())).thenReturn(Optional.ofNullable(null));

        wayService.deleteWay(way.getId());
    }

    @Test
    public void getAllWays() {
        List<Way> ways = List.of(new Way(), new Way(), new Way());

        when(wayRepository.findAll()).thenReturn(ways);
        wayService.getAllWays();
    }

    @Test
    public void addPartWay() {
        Short partNumber = 1;
        Way way = new Way();
        WayDescriptionInfoRequest request = new WayDescriptionInfoRequest();
        request.setPartNumber(partNumber);


        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));

        when(wayDescriptionRepository.findByWayIdPartNumber(way.getId(),request.getPartNumber())).thenReturn(Optional.ofNullable(null));

        DescriptionWay descriptionWay = new DescriptionWay();
        descriptionWay.setWay(way);
        descriptionWay.setId(1L);

        when(wayDescriptionRepository.save(any(DescriptionWay.class))).thenReturn(descriptionWay);

        WayDescriptionInfoResponse answer = wayService.addPartWay(way.getId(),request);

        assertEquals( descriptionWay.getId(), answer.getId() );
    }

    @Test(expected = CustomException.class)
    public void addPartWay_ifPartWayExist() {
        Short partNumber = 1;
        Way way = new Way();
        WayDescriptionInfoRequest request = new WayDescriptionInfoRequest();
        request.setPartNumber(partNumber);

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));

        DescriptionWay descriptionWay = new DescriptionWay();
        descriptionWay.setWay(way);
        descriptionWay.setId(1L);

        when(wayDescriptionRepository.findByWayIdPartNumber(way.getId(),request.getPartNumber())).thenReturn(Optional.of(descriptionWay));

        WayDescriptionInfoResponse answer = wayService.addPartWay(way.getId(),request);

        assertEquals( descriptionWay.getId(), answer.getId() );
    }

    @Test(expected = CustomException.class)
    public void addPartWay_ifWayNotExist() {
        Short partNumber = 1;
        Way way = new Way();
        WayDescriptionInfoRequest request = new WayDescriptionInfoRequest();
        request.setPartNumber(partNumber);

        when(wayRepository.findById(way.getId())).thenReturn(Optional.ofNullable(null));

        wayService.addPartWay(way.getId(),request);
    }

    @Test
    public void getPartsWay() {
        Way way = new Way();
        way.setId(1L);
        List<DescriptionWay> descriptionWays = List.of(new DescriptionWay(), new DescriptionWay());
        way.setDescriptionWays(descriptionWays);

        when(wayRepository.findById(way.getId())).thenReturn(Optional.of(way));
        wayService.getPartsWay(way.getId());
    }

    @Test(expected = CustomException.class)
    public void getPartsWay_ifWayNotExist() {
        Way way = new Way();
        way.setId(1L);

        when(wayRepository.findById(way.getId())).thenReturn(Optional.ofNullable(null));

        wayService.getPartsWay(way.getId());
    }

    @Test
    public void updatePartWay() {
        Short partNumber = 1;
        Way way = new Way();
        WayDescriptionInfoRequest request = new WayDescriptionInfoRequest();
        request.setPartNumber(partNumber);

        DescriptionWay descriptionWay = new DescriptionWay();
        descriptionWay.setWay(way);
        descriptionWay.setId(1L);

        when(wayDescriptionRepository.findByWayIdPartNumber(way.getId(),request.getPartNumber())).thenReturn(Optional.of(descriptionWay));

        when(wayDescriptionRepository.save(any(DescriptionWay.class))).thenReturn(descriptionWay);

        WayDescriptionInfoResponse answer = wayService.updatePartWay(way.getId(),request.getPartNumber(),request);

        assertEquals( descriptionWay.getId(), answer.getId() );
    }

    @Test
    public void updatePartWayByNameStreet() {
        Short partNumber = 1;
        Way way = new Way();
        WayDescriptionInfoRequest request = new WayDescriptionInfoRequest();
        request.setPartNumber(partNumber);
        request.setNameStreet("Test of street name.");

        DescriptionWay descriptionWay = new DescriptionWay();
        descriptionWay.setWay(way);
        descriptionWay.setId(1L);

        when(wayDescriptionRepository.findByWayIdPartNumber(way.getId(),request.getPartNumber())).thenReturn(Optional.of(descriptionWay));

        when(wayDescriptionRepository.save(any(DescriptionWay.class))).thenReturn(descriptionWay);

        WayDescriptionInfoResponse answer = wayService.updatePartWay(way.getId(),request.getPartNumber(),request);

        assertEquals( descriptionWay.getId(), answer.getId() );
    }

    @Test
    public void updatePartWayByPartStart() {
        Short partNumber = 1;
        Way way = new Way();
        WayDescriptionInfoRequest request = new WayDescriptionInfoRequest();
        request.setPartNumber(partNumber);
        request.setPartStart("Change part start name.");

        DescriptionWay descriptionWay = new DescriptionWay();
        descriptionWay.setWay(way);
        descriptionWay.setId(1L);

        when(wayDescriptionRepository.findByWayIdPartNumber(way.getId(),request.getPartNumber())).thenReturn(Optional.of(descriptionWay));

        when(wayDescriptionRepository.save(any(DescriptionWay.class))).thenReturn(descriptionWay);

        WayDescriptionInfoResponse answer = wayService.updatePartWay(way.getId(),request.getPartNumber(),request);

        assertEquals( descriptionWay.getId(), answer.getId() );
    }

    @Test
    public void updatePartWayByPartEnd() {
        Short partNumber = 1;
        Way way = new Way();
        WayDescriptionInfoRequest request = new WayDescriptionInfoRequest();
        request.setPartNumber(partNumber);
        request.setPartEnd("Change part end name.");

        DescriptionWay descriptionWay = new DescriptionWay();
        descriptionWay.setWay(way);
        descriptionWay.setId(1L);

        when(wayDescriptionRepository.findByWayIdPartNumber(way.getId(),request.getPartNumber())).thenReturn(Optional.of(descriptionWay));

        when(wayDescriptionRepository.save(any(DescriptionWay.class))).thenReturn(descriptionWay);

        WayDescriptionInfoResponse answer = wayService.updatePartWay(way.getId(),request.getPartNumber(),request);

        assertEquals( descriptionWay.getId(), answer.getId() );
    }

    @Test
    public void updatePartWayByManeuver2nextPart() {
        Short partNumber = 1;
        Way way = new Way();
        WayDescriptionInfoRequest request = new WayDescriptionInfoRequest();
        request.setPartNumber(partNumber);
        request.setManeuver2nextPart("Change maneuver.");

        DescriptionWay descriptionWay = new DescriptionWay();
        descriptionWay.setWay(way);
        descriptionWay.setId(1L);

        when(wayDescriptionRepository.findByWayIdPartNumber(way.getId(),request.getPartNumber())).thenReturn(Optional.of(descriptionWay));

        when(wayDescriptionRepository.save(any(DescriptionWay.class))).thenReturn(descriptionWay);

        WayDescriptionInfoResponse answer = wayService.updatePartWay(way.getId(),request.getPartNumber(),request);

        assertEquals( descriptionWay.getId(), answer.getId() );
    }

    @Test
    public void updatePartWayByPartLength() {
        Short partNumber = 1;
        Way way = new Way();
        WayDescriptionInfoRequest request = new WayDescriptionInfoRequest();
        request.setPartNumber(partNumber);
        request.setPartLength(1f);

        DescriptionWay descriptionWay = new DescriptionWay();
        descriptionWay.setWay(way);
        descriptionWay.setId(1L);

        when(wayDescriptionRepository.findByWayIdPartNumber(way.getId(),request.getPartNumber())).thenReturn(Optional.of(descriptionWay));

        when(wayDescriptionRepository.save(any(DescriptionWay.class))).thenReturn(descriptionWay);

        WayDescriptionInfoResponse answer = wayService.updatePartWay(way.getId(),request.getPartNumber(),request);

        assertEquals( descriptionWay.getId(), answer.getId() );
    }

    @Test(expected = CustomException.class)
    public void updatePartWay_ifPartWayNotExist() {
        Short partNumber = 1;
        Way way = new Way();

        when(wayDescriptionRepository.findByWayIdPartNumber(way.getId(),partNumber)).thenReturn(Optional.ofNullable(null));

        wayService.updatePartWay(way.getId(), partNumber, new WayDescriptionInfoRequest());
    }


    @Test
    public void deletePartWay() {
        DescriptionWay descriptionWay = new DescriptionWay();
        Short part = 1;
        when(wayDescriptionRepository.findByWayIdPartNumber(1L,part)).thenReturn(Optional.of(descriptionWay));

        wayService.deletePartWay(1L, part);
        verify(wayDescriptionRepository, times(1)).delete(any(DescriptionWay.class));
    }

    @Test
    public void deletePartWay_ifPartWatNotExists() {
        DescriptionWay descriptionWay = new DescriptionWay();
        Short part = 1;
        when(wayDescriptionRepository.findByWayIdPartNumber(1L,part)).thenReturn(Optional.ofNullable(null));

        wayService.deletePartWay(1L, part);
    }
}