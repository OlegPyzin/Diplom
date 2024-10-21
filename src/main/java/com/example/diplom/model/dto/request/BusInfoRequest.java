package com.example.diplom.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusInfoRequest {
    String model;
    String description;
    String regNumber;
    Short  capacity;
    Date   dateMade;
    String vin;
}
