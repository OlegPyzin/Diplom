package com.example.diplom.model.dto.request;

import com.example.diplom.model.enums.MedicalStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MedicalInfoRequest {
    String whoMadeMedical;
    MedicalStatus status;
    String descriptionStatus;
}
