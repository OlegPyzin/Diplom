package com.example.diplom.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PassengerInfoRequest {
    String nic;
    String firstName;
    String lastName;
    String middleName;
    String password;
    String email;
    String telephone;
}
