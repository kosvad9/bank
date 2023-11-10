package com.kosvad9.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id_user")
public class Client extends User{
    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String passportNumber;

    @Column(unique = true, nullable = false)
    private String passportId;

    private LocalDate passportDate;

    @Builder.Default
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

    @Builder
    public Client(Long id, String phoneNumber, String password, String firstName, String lastName, String patronymic, LocalDate birthDate, String passportNumber, String passportId, LocalDate passportDate) {
        super(id, phoneNumber, password, firstName, lastName, patronymic);
        this.birthDate = birthDate;
        this.passportNumber = passportNumber;
        this.passportId = passportId;
        this.passportDate = passportDate;
    }
}
