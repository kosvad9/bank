package com.kosvad9.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClientCreateDto(
                      @NotNull @Pattern(regexp = "\\+375(29|33|44)\\d{7}") String phoneNumber,
                      @NotBlank String password,
                      @NotBlank String firstName,
                      @NotBlank String lastName,
                              String patronymic,
                      @Past   LocalDate birthDate,
                      @NotBlank @Size(min = 9, max = 9) String passportNumber,
                              String passportId,
                              LocalDate passportDate) {
}
