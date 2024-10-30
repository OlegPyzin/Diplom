package com.example.diplom.model.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "work_onways", schema = "administrators")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class WorkOnWay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @JsonBackReference(value = "driver_workonway_driver")
    Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    @JsonBackReference(value = "driver_workonway_bus")
    Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "way_id")
    @JsonBackReference(value = "driver_workonway_way")
    Way way;

    @NotNull
    @Column(name = "date_work_day")
    LocalDate dateWorkDay;

    @Column(name = "time_start")
    Time timeStart;

    @Column(name = "time_end")
    Time timeEnd;

    @Column(name = "numbers_ways")
    Short numbersWay;
}
