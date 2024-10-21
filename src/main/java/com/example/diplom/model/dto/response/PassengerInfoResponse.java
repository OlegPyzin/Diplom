package com.example.diplom.model.dto.response;

import com.example.diplom.model.enums.PassengerStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PassengerInfoResponse {
    Long id;
    PassengerStatus status;
}
