package com.kosvad9.mapper;

import com.kosvad9.database.entity.Application;
import com.kosvad9.database.entity.Client;
import com.kosvad9.database.entity.CreditProgram;
import com.kosvad9.dto.ApplicationDto;
import com.kosvad9.dto.ClientDto;
import com.kosvad9.dto.CreditProgramDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class ApplicationDtoMapper implements Mapper<Application, ApplicationDto>{
    private final Mapper<Client, ClientDto> clientDtoMapper;
    private final Mapper<CreditProgram, CreditProgramDto> creditProgramDtoMapper;

    @Override
    public ApplicationDto map(Application value) {
        return new ApplicationDto(value.getId(),
                value.getDate(),
                value.getAmount(),
                value.getPeriodMonth(),
                clientDtoMapper.map(value.getClient()),
                creditProgramDtoMapper.map(value.getProgram()));
    }
}
