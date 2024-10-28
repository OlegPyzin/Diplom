package com.example.diplom.model.db.entity;

import com.example.diplom.model.enums.PassengerStatus;
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
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "passengers", schema = "passengers")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty
    @Column(name = "nic", length = 50)
    String nic;

    @Column(name = "last_name", length = 50)
    String lastName;

    @Column(name = "first_name", length = 50)
    String firstName;

    @Column(name = "middle_name", length = 50)
    String middleName;

    @NotEmpty
    @Column(name = "password", length = 50)
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "telephone", length = 50)
    String telephone;

    @Column(name = "date_added")
    @CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateAdded;

    @Column(name = "date_modified")
    @UpdateTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateModified;

    @Column(name = "date_deleted")
    @UpdateTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateDeleted;

    @Column(name = "status", length = 30)
    @Enumerated(EnumType.STRING)
    PassengerStatus status;
}
