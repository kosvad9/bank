package com.kosvad9.database.repository;

import com.kosvad9.database.entity.Currency;
import com.kosvad9.dto.CurrencyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    List<CurrencyDto> findAllBy();
}
