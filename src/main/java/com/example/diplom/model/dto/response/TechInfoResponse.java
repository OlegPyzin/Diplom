package com.example.diplom.model.dto.response;

import com.example.diplom.model.dto.request.TechInfoRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TechInfoResponse extends TechInfoRequest {
    LocalDateTime dateTechControl;
    LocalDateTime dateNextTechControl;
}
