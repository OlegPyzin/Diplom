package com.example.diplom.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// Does not send NULL data
/// Needed for a test to avoid crush
@JsonIgnoreProperties(ignoreUnknown = true)
public class WayDescriptionInfoRequest {
    Short partNumber;
    String nameStreet;
    String partStart;
    String partEnd;
    String maneuver2nextPart;
    Float partLength;
}
