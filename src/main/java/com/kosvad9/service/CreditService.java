package com.kosvad9.service;

import com.kosvad9.database.entity.Credit;
import com.kosvad9.database.repository.AccountRepository;
import com.kosvad9.database.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Transactional
@Service
public class CreditService {
    private final CreditRepository creditRepository;
    private final AccountRepository accountRepository;
    public Integer countPaidCredits(Long clientId){
        return creditRepository.countPaidCredits(clientId);
    }

    public Integer countNotPaidCredits(Long clientId){
        return creditRepository.countNotPaidCredits(clientId);
    }

    public BigDecimal getNextPaymentAmount(Long creditId){
        Credit credit = creditRepository.getReferenceById(creditId);
        BigDecimal nextPayment = getNextPaymentAmountWithoutPercent(credit);
        return nextPayment.add(getNextPaymentAmountPercent(credit, nextPayment));
    }

    private BigDecimal getNextPaymentAmountWithoutPercent(Credit credit){
        if (credit.getLastPaymentDate().getMonth() == LocalDate.now().getMonth() &&
            credit.getLastPaymentDate().getYear() == LocalDate.now().getYear() ) return BigDecimal.ZERO;
        long monthRemain = ChronoUnit.MONTHS.between(LocalDate.now(), credit.getDateEnd());
        return credit.getDebt().divide(new BigDecimal(monthRemain),2, RoundingMode.FLOOR);
    }

    private BigDecimal getNextPaymentAmountPercent(Credit credit, BigDecimal nextPayment){
        return nextPayment.multiply(BigDecimal.valueOf(credit.getInterestRate() / 100d));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean pay(Long creditId, Long accountId, BigDecimal sum){
        Credit credit = creditRepository.getReferenceById(creditId);
        BigDecimal nextPayment = getNextPaymentAmountWithoutPercent(credit);
        BigDecimal percent = getNextPaymentAmountPercent(credit, nextPayment);
        accountRepository.subtractMoney(accountId, sum);
        //будем считать что отнятые проценты ушли на счет банка
        sum = sum.subtract(percent);
        creditRepository.pay(creditId, sum, LocalDate.now());
        return true;
    }
}
