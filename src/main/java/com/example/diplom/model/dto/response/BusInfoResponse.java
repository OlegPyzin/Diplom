package com.example.diplom.model.dto.response;

import com.example.diplom.model.dto.request.BusInfoRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BusInfoResponse extends BusInfoRequest {
    Long id;
    Short status;
}
