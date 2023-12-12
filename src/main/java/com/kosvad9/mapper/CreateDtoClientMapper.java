package com.kosvad9.mapper;

import com.kosvad9.configuration.PasswordEncoderConfiguration;
import com.kosvad9.database.entity.Client;
import com.kosvad9.dto.ClientCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateDtoClientMapper implements Mapper<ClientCreateDto, Client> {
    private final PasswordEncoder passwordEncoder;
    @Override
    public Client map(ClientCreateDto value) {
        Client client =  Client.builder()
                .phoneNumber(value.phoneNumber())
                .firstName(value.firstName())
                .lastName(value.lastName())
                .patronymic(value.patronymic())
                .birthDate(value.birthDate())
                .passportNumber(value.passportNumber())
                .passportId(value.passportId())
                .passportDate(value.passportDate()).build();
        Optional.ofNullable(value.password())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(client::setPassword);
        return client;
    }
}
