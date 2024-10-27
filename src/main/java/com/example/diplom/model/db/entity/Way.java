package com.example.diplom.model.db.entity;

import com.example.diplom.model.enums.*;
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

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "ways", schema = "financial")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Way {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "description")
    String description;

    @NotEmpty
    @Column(name = "way_number", length = 50)
    String wayNumber;

    @NotNull
    @Column(name = "way_length")
    Float wayLength;

    @NotNull
    @Column(name = "way_cost")
    BigDecimal cost;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "way")
    List<DescriptionWay> partsWay;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "way")
    List<WorkOnWay> workOnWay;
}
