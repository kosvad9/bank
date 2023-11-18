package com.kosvad9.dto;

import java.time.LocalDate;

public record ClientDto(Long id,
                        String phoneNumber,
                        String password,
                        String firstName,
                        String lastName,
                        String patronymic,
                        LocalDate birthDate,
                        String passportNumber,
                        String passportId,
                        LocalDate passportDate) {
}
