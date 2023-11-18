package com.kosvad9.mapper;

import com.kosvad9.database.entity.Client;
import com.kosvad9.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoMapper implements Mapper<Client, ClientDto> {
    @Override
    public ClientDto map(Client value) {
        return new ClientDto(value.getId(),
                value.getPhoneNumber(),
                value.getPassword(),
                value.getFirstName(),
                value.getLastName(),
                value.getPatronymic(), 
                value.getBirthDate(),
                value.getPassportInfo().getPassportNumber(),
                value.getPassportInfo().getPassportId(),
                value.getPassportInfo().getPassportDate());
    }
}
