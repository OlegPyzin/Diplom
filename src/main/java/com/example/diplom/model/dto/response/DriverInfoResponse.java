package com.example.diplom.model.dto.response;

import com.example.diplom.model.dto.request.DriverInfoRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DriverInfoResponse extends DriverInfoRequest {
    Long id;
    Short status;
}
