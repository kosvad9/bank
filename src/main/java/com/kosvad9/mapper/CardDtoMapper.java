package com.kosvad9.mapper;

import com.kosvad9.database.entity.Card;
import com.kosvad9.dto.CardDto;
import org.springframework.stereotype.Component;

@Component
public class CardDtoMapper implements Mapper<Card, CardDto> {
    @Override
    public CardDto map(Card value) {
        return new CardDto(value.getId(),
                value.getBillingSystem().toString(),
                value.getNumber(),
                value.getExpirationDate(),
                value.getCvv(),
                value.getStatus().toString());
    }
}
