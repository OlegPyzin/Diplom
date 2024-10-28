package com.example.diplom.model.dto.response;

import com.example.diplom.model.dto.request.MedicalInfoRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MedicalInfoResponse extends MedicalInfoRequest {
    LocalDateTime dateExamination;
}
