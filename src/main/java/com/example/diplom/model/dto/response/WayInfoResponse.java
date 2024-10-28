package com.example.diplom.model.dto.response;

import com.example.diplom.model.dto.request.WayInfoRequest;
import com.example.diplom.model.enums.WayStatus;
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

public class WayInfoResponse extends WayInfoRequest {
    Long id;
    WayStatus status;
    List<WayDescriptionInfoResponse> wayDescriptionInfoResponseList;
}
