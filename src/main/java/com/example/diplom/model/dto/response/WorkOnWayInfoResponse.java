package com.example.diplom.model.dto.response;

import com.example.diplom.model.db.entity.Bus;
import com.example.diplom.model.dto.request.WayDescriptionInfoRequest;
import com.example.diplom.model.dto.request.WorkOnWayInfoRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
// Does not send NULL data
/// Needed for a test to avoid crush
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkOnWayInfoResponse extends WorkOnWayInfoRequest {
//    Bus bus;
//    LocalDate dateWorkDay;
//    Time timeStart;
//    Time timeEnd;
    Long id;
}
