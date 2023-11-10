package com.kosvad9.database.entity;

import com.kosvad9.database.enums.BillingSystem;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"billingSystem", "number", "expirationDate"})
@EqualsAndHashCode(of = "number")
@Builder
@Entity
public class Card implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BillingSystem billingSystem;

    @Column(nullable = false, unique = true)
    private String number;

    private LocalDate expirationDate;

    private String cvv;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;
}
