package com.example.diplom.model.dto.response;

import com.example.diplom.model.db.entity.Bus;
import com.example.diplom.model.dto.request.WayDescriptionInfoRequest;
import com.example.diplom.model.dto.request.WorkOnWayInfoRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.time.LocalDate;

import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class WorkOnWayInfoResponse extends WorkOnWayInfoRequest {
//    Bus bus;
//    LocalDate dateWorkDay;
//    Time timeStart;
//    Time timeEnd;
    Long id;
}
