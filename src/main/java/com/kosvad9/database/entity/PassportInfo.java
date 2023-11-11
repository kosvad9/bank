package com.kosvad9.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PassportInfo {
    @Column(unique = true, nullable = false)
    private String passportNumber;

    @Column(unique = true, nullable = false)
    private String passportId;

    private LocalDate passportDate;
}
