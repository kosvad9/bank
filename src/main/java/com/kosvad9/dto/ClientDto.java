package com.kosvad9.dto;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

public record ClientDto(Long id,
                        String phoneNumber,
                        String firstName,
                        String lastName,
                        String patronymic,
                        LocalDate birthDate,
                        String passportNumber,
                        String passportId,
                        LocalDate passportDate,
                        String image){ }
