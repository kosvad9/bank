package com.kosvad9.service;

import com.kosvad9.database.entity.Account;
import com.kosvad9.database.entity.Card;
import com.kosvad9.database.enums.StatusCard;
import com.kosvad9.database.repository.AccountRepository;
import com.kosvad9.database.repository.CardRepository;
import com.kosvad9.dto.AccountDto;
import com.kosvad9.dto.CardCreateDto;
import com.kosvad9.dto.CardDto;
import com.kosvad9.dto.ClientDto;
import com.kosvad9.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Transactional
@Service
public class CardService {
    private final CardRepository cardRepository;
    private final Mapper<Card, CardDto> cardDtoMapper;
    private final AccountRepository accountRepository;
    @Value("${card.bin}")
    private final String bin;

    public boolean P2P(CardDto card, String number){
        return false;
    }

    public CardDto getCard(Long cardId){
        return cardRepository.findById(cardId).map(cardDtoMapper::map).orElse(null);
    }

    public List<CardDto> getAccountCards(Long accountId){
        return cardRepository.findCardsByAccount_Id(accountId).stream().map(cardDtoMapper::map).toList();
    }

    public CardDto createCard(Long accountId, CardCreateDto cardCreate){
        Account account = accountRepository.getReferenceById(accountId);
        Random rnd = new Random();
        String cvv = String.format("%3s", rnd.nextInt(1,1000)).replace(' ','0');
        Card card = Card.builder()
                .account(account)
                .billingSystem(cardCreate.billingSystem())
                .expirationDate(ChronoUnit.YEARS.addTo(LocalDate.now(), cardCreate.periodYears()))
                .status(StatusCard.ACTIVE)
                .number("")
                .cvv(cvv)
                .build();
        return cardDtoMapper.map(cardRepository.save(card));
    }

    private String generateCardNumber(char billingSystemDigit){
        Long identifier = cardRepository.getNextCardIdentifier();
        String accIdentifier = String.format("%9s", identifier.toString()).replace(' ', '0');
        String cardNumberWithoutChecksum = billingSystemDigit + bin + accIdentifier;
        return cardNumberWithoutChecksum + generateChecksum(cardNumberWithoutChecksum);
    }

    private char generateChecksum(String cardNumberWithoutChecksum){
        int[] array = Arrays.stream(cardNumberWithoutChecksum.split(""))
                .mapToInt(ch -> Integer.valueOf(ch.toString()))
                .toArray();
        for (int i = 0; i < array.length; i += 2){
            array[i] *= 2;
            if (array[i] > 9) array[i] = array[i]%10+1;
        }
        int sum = Arrays.stream(array).reduce(Integer::sum).getAsInt();
        int checksum = 10 - sum % 10;
        return checksum == 10 ? '0' : Character.forDigit(checksum, 10);
    }

    public boolean blockCard(Long cardId){
        Optional<Card> maybeCard = cardRepository.findById(cardId);
        maybeCard.ifPresent(card -> card.setStatus(StatusCard.BLOCKED));
        maybeCard.ifPresent(cardRepository::save);
        return maybeCard.isPresent();
    }

    public boolean actionProhibition(Long cardId){
        Card card = cardRepository.getReferenceById(cardId);
        if (card.getStatus() != StatusCard.BLOCKED){
            card.setStatus(StatusCard.ACTION_PROHIBITED);
            cardRepository.save(card);
            return true;
        }
        else
            return false;
    }

    public boolean activeCard(Long cardId){
        Card card = cardRepository.getReferenceById(cardId);
        if (card.getStatus() != StatusCard.BLOCKED){
            card.setStatus(StatusCard.ACTIVE);
            cardRepository.save(card);
            return true;
        }
        else
            return false;
    }
}
