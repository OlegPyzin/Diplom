package com.example.diplom.model.dto.response;

import com.example.diplom.model.dto.request.WayInfoRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class WayInfoResponse extends WayInfoRequest {
    Long id;
}
