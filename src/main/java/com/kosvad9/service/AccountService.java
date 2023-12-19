package com.kosvad9.service;

import com.kosvad9.database.entity.Account;
import com.kosvad9.database.entity.Client;
import com.kosvad9.database.entity.Currency;
import com.kosvad9.database.enums.StatusAccount;
import com.kosvad9.database.repository.AccountRepository;
import com.kosvad9.database.repository.ClientRepository;
import com.kosvad9.database.repository.CurrencyRepository;
import com.kosvad9.dto.AccountCreateDto;
import com.kosvad9.dto.AccountDto;
import com.kosvad9.mapper.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class AccountService {
    private final Mapper<Account, AccountDto> accountDtoMapper;
    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final ClientRepository clientRepository;
    @Value("${iban.countryDigits}")
    private String countryDigits;
    @Value("${iban.country}")
    private String country;
    @Value("${iban.bankDigits}")
    private String bankDigits;
    @Value("${iban.bank}")
    private String bank;

    public AccountDto createAccount(AccountCreateDto accountCreateDto){
        Optional<Currency> maybeCurrency = currencyRepository.findById(accountCreateDto.currencyId());
        Optional<Client> maybeClient = clientRepository.findById(accountCreateDto.clientId());
        Account account = Account.builder()
                .amount(BigDecimal.ZERO)
                .dateCreate(LocalDate.now())
                .status(StatusAccount.ACTIVE)
                .iban(generateIBAN())
                .build();
        maybeCurrency.ifPresent(account::setCurrency);
        maybeClient.ifPresent(account::setClient);
        return accountDtoMapper.map(accountRepository.save(account));
    }

    public void createAccountOfCredit(Long clientId, BigDecimal sum){
        Client client = clientRepository.getReferenceById(clientId);
        Optional<Currency> maybeCurrency = currencyRepository.getCurrencyByCode("BYN");
        Account account = Account.builder()
                .amount(sum)
                .dateCreate(LocalDate.now())
                .status(StatusAccount.ACTIVE)
                .iban(generateIBAN())
                .build();
        maybeCurrency.ifPresent(account::setCurrency);
        accountRepository.save(account);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void fillingUpAccount(Long accountId, BigDecimal sum){
        accountRepository.addMoney(accountId, sum);
    }

    public AccountDto getAccount(Long accountId){
        return accountRepository.findById(accountId).map(accountDtoMapper::map).orElse(null);
    }

    public boolean blockAccount(Long accountId){
        Optional<Account> maybeAccount = accountRepository.findById(accountId);
        maybeAccount.ifPresent(account -> account.setStatus(StatusAccount.CLOSED));
        maybeAccount.ifPresent(accountRepository::save);
        return maybeAccount.isPresent();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void moneyTransfer(Long accountIdFrom, String accountIbanTo, BigDecimal sum){
        Account accountTo = accountRepository.findAccountByIban(accountIbanTo).orElse(null);
        if (accountTo == null) throw new RuntimeException("Аккаунт получателя не найден!");
        try {
            accountRepository.subtractMoney(accountIdFrom, sum);
            accountRepository.addMoney(accountTo.getId(), sum);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось выполнить перевод! Возможно не хватает средств!");
        }
    }

    private String generateIBAN(){
        BigInteger number = accountRepository.getNewAccountNumber();
        String numberStr = String.format("%20s", number.toString()).replace(' ', '0');
        String ibanDigitsStr = countryDigits + "00" + bankDigits + numberStr;
        BigInteger ibanDigits = new BigInteger(ibanDigitsStr);
        int checkSum = 98 - ibanDigits.mod(BigInteger.valueOf(97)).intValue();
        return country + checkSum + bank + numberStr;
    }
}
