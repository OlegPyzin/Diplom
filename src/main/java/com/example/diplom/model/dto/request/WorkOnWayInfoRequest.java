package com.example.diplom.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class WorkOnWayInfoRequest {
    Long wayId;
    Long driverId;
    Long busId;
    Time timeStart;
    Time timeEnd;
    LocalDate dateWorkDay;
}
