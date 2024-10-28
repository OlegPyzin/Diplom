package com.example.diplom.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class WayDescriptionInfoRequest {
    Short partNumber;
    String nameStreet;
    String partStart;
    String partEnd;
    String maneuver2nextPart;
    Float partLength;
}
