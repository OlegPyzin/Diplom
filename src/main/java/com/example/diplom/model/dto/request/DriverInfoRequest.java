package com.example.diplom.model.dto.request;

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
public class DriverInfoRequest {
    String lastName;
    String firstName;
    String middleName;
    Date birthDay;
    String driveLicense;
    Date driveLicenseValid;
    String homeAddress;
    String telephone;
}
