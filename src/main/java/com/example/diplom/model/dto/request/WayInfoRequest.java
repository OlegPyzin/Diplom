package com.example.diplom.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// Does not send NULL data
/// Needed for a test to avoid crush
@JsonIgnoreProperties(ignoreUnknown = true)
public class WayInfoRequest {
    String description;
    String wayNumber;
    Float wayLength;
    BigDecimal cost;
}
