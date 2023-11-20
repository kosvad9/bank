package com.kosvad9.mapper;

import com.kosvad9.database.entity.CreditProgram;
import com.kosvad9.dto.CreditProgramDto;
import org.springframework.stereotype.Component;


@Component
public class CreditProgramDtoMapper implements Mapper<CreditProgram, CreditProgramDto> {
    @Override
    public CreditProgramDto map(CreditProgram value) {
        return new CreditProgramDto(value.getId(),
                value.getDescription(),
                value.getInterestRate(),
                value.getMaxAmount(),
                value.getMaxPeriodMonth());
    }
}
