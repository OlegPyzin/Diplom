package com.example.diplom.model.db.entity;

import com.example.diplom.model.enums.WayStatus;
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
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "description_ways", schema = "financial")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DescriptionWay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "way_id")
    @JsonBackReference(value = "driver_ways")
    Way way;

    @NotNull
    @Column(name = "part_number")
    Short partNumber;

    @NotEmpty
    @Column(name = "name_street")
    String nameStreet;

    @NotEmpty
    @Column(name = "part_start")
    String partStart;

    @NotEmpty
    @Column(name = "part_end")
    String partEnd;

    @Column(name = "maneuver_2next_part")
    String maneuver2nextPart;

    @NotNull
    @Column(name = "part_length")
    Float partLength;

    @Column(name = "date_added")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateAdded;

    @Column(name = "date_modified")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateModified;

    @Column(name = "date_deleted")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateDeleted;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    WayStatus status;
}
