package com.kosvad9.dto;

import java.time.LocalDate;

public record ClientCreateDto(String phoneNumber,
                              String password,
                              String firstName,
                              String lastName,
                              String patronymic,
                              LocalDate birthDate,
                              String passportNumber,
                              String passportId,
                              LocalDate passportDate) {
}
