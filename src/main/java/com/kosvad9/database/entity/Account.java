package com.kosvad9.database.entity;

import com.kosvad9.database.enums.StatusAccount;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id","iban","amount"})
@EqualsAndHashCode(of = "iban")
@Builder
@Entity
public class Account implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(nullable = false, unique = true)
    private String iban;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_currency")
    private Currency currency;

    private LocalDate dateCreate;

    private LocalDate dateClose;

    @Enumerated(EnumType.STRING)
    private StatusAccount status;

    @Builder.Default
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Card> cards = new ArrayList<>();
}
