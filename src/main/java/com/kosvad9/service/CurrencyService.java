package com.kosvad9.service;

import com.kosvad9.database.repository.CurrencyRepository;
import com.kosvad9.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public List<CurrencyDto> getAllCurrencies(){
        return currencyRepository.findAllBy();
    }
}
