package com.example.diplom.model.db.entity;

import com.example.diplom.model.enums.WayStatus;
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
    Way way;

    @NotNull
    @Column(name = "part_number")
    Short partNumber;

    @Column(name = "name_street")
    String nameStreet;

    @Column(name = "part_start")
    String partStart;

    @Column(name = "part_end")
    String partEnd;

    @Column(name = "maneuver_2next_part")
    String maneuver2nextPart;

    @NotNull
    @Column(name = "part_length")
    Float partLength;

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

    @NotEmpty
    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    WayStatus status;
}
