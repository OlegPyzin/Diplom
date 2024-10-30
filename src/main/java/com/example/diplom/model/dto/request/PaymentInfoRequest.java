package com.example.diplom.model.dto.request;

import com.example.diplom.model.enums.PaymentMethod;
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

public class PaymentInfoRequest {
    Long wayId;
    Long busId;
    PaymentMethod paymentMethod;
    BigDecimal amount;
    String amountInfo;
}
