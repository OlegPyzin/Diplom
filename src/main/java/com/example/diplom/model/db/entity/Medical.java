package com.example.diplom.model.db.entity;

import com.example.diplom.model.enums.MedicalStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "medical", schema = "administrators")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Medical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @JsonBackReference(value = "driver_drivers")
    Driver driver;

    @NotEmpty
    @Column(name = "who_made_medical")
    String whoMadeMedical;

    @Column(name = "date_examination")
    //@CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateExamination;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    MedicalStatus status;

    @NotEmpty
    @Column(name = "description_status")
    String descriptionStatus;
}
