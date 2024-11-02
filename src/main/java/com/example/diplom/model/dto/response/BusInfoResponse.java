package com.example.diplom.model.dto.response;

import com.example.diplom.model.db.entity.Bus;
import com.example.diplom.model.dto.request.BusInfoRequest;
import com.example.diplom.model.enums.BusStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// Does not send NULL data
// Needed for a test to avoid crush
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusInfoResponse extends BusInfoRequest {
    Long id;
    BusStatus status;
    List<TechInfoResponse> techInfoResponseList;
}
