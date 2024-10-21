package com.example.diplom.model.dto.request;

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

public class PaymentRequest {
    Long passengerId;
    Long driverId;
    Long busId;
    Long wayId;
    BigDecimal amount;
    String amountInfo;
}
