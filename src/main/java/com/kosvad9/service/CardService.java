package com.kosvad9.service;

import com.kosvad9.dto.AccountDto;
import com.kosvad9.dto.CardCreateDto;
import com.kosvad9.dto.CardDto;
import com.kosvad9.dto.ClientDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CardService {
    public boolean P2P(CardDto card, String number){
        return false;
    }

    public List<CardDto> getUserCards(ClientDto client){
        return null;
    }

    public List<CardDto> getAccountCards(AccountDto account){
        return null;
    }

    public CardDto createCard(CardCreateDto cardCreate){
        return null;
    }

    public CardDto blockCard(CardDto card){
        return null;
    }

    public CardDto actionProhibition(CardDto card){
        return null;
    }
}
