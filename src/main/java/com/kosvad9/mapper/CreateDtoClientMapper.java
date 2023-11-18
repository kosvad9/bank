package com.kosvad9.mapper;

import com.kosvad9.database.entity.Client;
import com.kosvad9.dto.ClientCreateDto;
import org.springframework.stereotype.Component;

@Component
public class CreateDtoClientMapper implements Mapper<ClientCreateDto, Client> {
    @Override
    public Client map(ClientCreateDto value) {
        return Client.builder()
                .phoneNumber(value.phoneNumber())
                .password(value.password())
                .firstName(value.firstName())
                .lastName(value.lastName())
                .patronymic(value.patronymic())
                .birthDate(value.birthDate())
                .passportNumber(value.passportNumber())
                .passportId(value.passportId())
                .passportDate(value.passportDate()).build();
    }
}
