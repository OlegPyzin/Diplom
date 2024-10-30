package com.example.diplom.model.dto.response;

import com.example.diplom.model.dto.request.PaymentInfoRequest;
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

public class PaymentInfoResponse extends PaymentInfoRequest {
    Long id;
    Date datePayment;
}
