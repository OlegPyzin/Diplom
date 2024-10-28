package com.example.diplom.model.db.entity;

import com.example.diplom.model.enums.DriverStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "drivers", schema = "buses")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty
    @Column(name = "last_name", length = 50)
    String lastName;

    @NotEmpty
    @Column(name = "first_name", length = 50)
    String firstName;

    @Column(name = "middle_name", length = 50)
    String middleName;

    @NotNull
    @Column(name = "date_birth")
    Date birthDay;

    @NotEmpty
    @Column(name = "drive_license")
    String driveLicense;

    @NotNull
    @Column(name = "drive_license_valid")
    Date driveLicenseValid;

    @NotEmpty
    @Column(name = "home_address")
    String homeAddress;

    @NotEmpty
    @Column(name = "telephone", length = 50)
    String telephone;

    @Column(name = "date_added")
    //@CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateAdded;

    @Column(name = "date_modified")
    //@UpdateTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateModified;

    @Column(name = "date_fired")
    //@UpdateTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateFired;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    DriverStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    @JsonBackReference(value = "driver_drivers")
    List<Medical> medicals;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    List<WorkOnWay> workOnWay;
}
