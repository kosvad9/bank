package com.kosvad9.mapper;

import com.kosvad9.database.entity.Account;
import com.kosvad9.dto.AccountDto;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoMapper implements Mapper<Account, AccountDto> {
    @Override
    public AccountDto map(Account value) {
        return new AccountDto(value.getId(),
                value.getIban(),
                value.getAmount(),
                value.getCurrency().getCode(),
                value.getStatus().name());
    }
}
