package com.kosvad9.database.entity;

import com.kosvad9.database.enums.StatusApplication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"client","program"})
@EqualsAndHashCode(exclude = {"client","program"})
@Builder
@Entity
public class Application implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_program")
    private CreditProgram program;

    @Enumerated(EnumType.STRING)
    private StatusApplication status;

    private String description;
}
