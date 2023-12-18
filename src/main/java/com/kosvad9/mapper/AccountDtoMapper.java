package com.kosvad9.mapper;

import com.kosvad9.database.entity.Account;
import com.kosvad9.database.entity.Card;
import com.kosvad9.dto.AccountDto;
import com.kosvad9.dto.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDtoMapper implements Mapper<Account, AccountDto> {
    private final Mapper<Card, CardDto> cardDtoMapper;
    @Override
    public AccountDto map(Account value) {
        return new AccountDto(value.getId(),
                value.getIban(),
                value.getAmount(),
                value.getCurrency().getCode(),
                value.getStatus().name(),
                value.getCards().stream().map(cardDtoMapper::map).toList());
    }
}
