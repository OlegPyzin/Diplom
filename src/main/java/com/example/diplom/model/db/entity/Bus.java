package com.example.diplom.model.db.entity;

import com.example.diplom.model.enums.*;
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
@Table(name = "buses", schema = "buses")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty
    @Column(name = "model", length = 50)
    String model;

    @Column(name = "description")
    String description;

    @NotEmpty
    @Column(name = "reg_number", length = 50)
    String regNumber;

    @NotNull
    @Column(name = "capacity")
    Short capacity;

    @Column(name = "date_made")
    Date dateMade;

    @NotEmpty
    @Column(name = "vin", length = 50)
    String vin;


    @Column(name = "date_added")
    //@CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateAdded;

    @Column(name = "date_modified")
    //@UpdateTimestamp
    // С этой аннотацией добавляется дата при добавлении информации
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateModified;

    @Column(name = "date_sold")
    //@UpdateTimestamp
    // С этой аннотацией добавляется дата при добавлении информации
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateSold;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    BusStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bus")
    @JsonBackReference(value = "driver_buses")
    List<TechControl> techControl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bus")
    List<WorkOnWay> workOnWay;
}
