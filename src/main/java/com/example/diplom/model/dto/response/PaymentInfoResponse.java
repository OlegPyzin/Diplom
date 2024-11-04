package com.example.diplom.model.dto.response;

import com.example.diplom.model.dto.request.PaymentInfoRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
// Does not send NULL data
// Needed for a test to avoid crush
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentInfoResponse extends PaymentInfoRequest {
    Long id;
    Date datePayment;
}
