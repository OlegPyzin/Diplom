package com.example.diplom.model.dto.response;

import com.example.diplom.model.dto.request.DriverInfoRequest;
import com.example.diplom.model.enums.DriverStatus;
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

public class DriverInfoResponse extends DriverInfoRequest {
    Long id;
    DriverStatus status;
    List<MedicalInfoResponse> medicalInfoResponseList;
}
