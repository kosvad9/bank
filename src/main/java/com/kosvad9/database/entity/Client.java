package com.kosvad9.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true, exclude = {"accounts","credits","applications"})
@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "id_user")
public class Client extends User{
    private LocalDate birthDate;

    @Embedded
    private PassportInfo passportInfo;

    private String image;

    @Builder.Default
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<Credit> credits = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<Application> applications = new ArrayList<>();

    public Client() {
    }

    @Builder
    public Client(Long id, String phoneNumber, String password, String firstName, String lastName, String patronymic, LocalDate birthDate, String passportNumber, String passportId, LocalDate passportDate, String image) {
        super(id, phoneNumber, password, firstName, lastName, patronymic);
        this.birthDate = birthDate;
        this.image = image;
        this.passportInfo = PassportInfo.builder().
                passportNumber(passportNumber).
                passportId(passportId).
                passportDate(passportDate).
                build();
    }
}
