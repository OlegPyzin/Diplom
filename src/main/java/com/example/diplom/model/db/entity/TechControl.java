package com.example.diplom.model.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "tech_control", schema = "buses")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TechControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    @JsonBackReference(value = "driver_buses")
    Bus bus;

    @NotNull
    @Column(name = "who_made_tech_control")
    String whoMadeTechControl;

    @Column(name = "date_tech_control")
    LocalDateTime dateTechControl;

    @Column(name = "date_next_tech_control")
    LocalDateTime dateNextTechControl;
}
