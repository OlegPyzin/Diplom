package com.example.diplom.model.dto.response;

import com.example.diplom.model.dto.request.WayDescriptionInfoRequest;
import com.example.diplom.model.enums.WayStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class WayDescriptionInfoResponse extends WayDescriptionInfoRequest {
    Long id;
    WayStatus status;
}
