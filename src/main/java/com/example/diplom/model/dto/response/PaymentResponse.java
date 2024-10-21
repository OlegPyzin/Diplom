package com.example.diplom.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PaymentResponse {
    Date datePayment;
}
