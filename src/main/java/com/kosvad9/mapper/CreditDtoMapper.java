package com.kosvad9.mapper;

import com.kosvad9.database.entity.Credit;
import com.kosvad9.dto.CreditDto;
import com.kosvad9.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditDtoMapper implements Mapper<Credit, CreditDto> {
    private CreditService creditService;
    @Override
    public CreditDto map(Credit value) {
        return new CreditDto(value.getId(),
                value.getAmount(),
                value.getDebt(),
                value.getDateEnd(),
                value.getInterestRate(),
                creditService.getNextPaymentAmount(value.getId()));
    }

    public void setCreditService(CreditService creditService) {
        this.creditService = creditService;
    }
}
