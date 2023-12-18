package com.kosvad9.controller;

import com.kosvad9.database.enums.BillingSystem;
import com.kosvad9.dto.AccountCreateDto;
import com.kosvad9.dto.AccountDto;
import com.kosvad9.dto.CardCreateDto;
import com.kosvad9.dto.ClientDto;
import com.kosvad9.service.AccountService;
import com.kosvad9.service.CardService;
import com.kosvad9.service.ClientService;
import com.kosvad9.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestMapping("/accounts")
@Controller
@SessionAttributes({"user"})
@RequiredArgsConstructor
public class AccountsController {
    private final ClientService clientService;
    private final CardService cardService;
    private final AccountService accountService;
    private final CurrencyService currencyService;
    @GetMapping
    public String allAccounts(Model model, Principal principal){
        ClientDto client = clientService.getClient(Long.parseLong(principal.getName()));
        model.addAttribute("user", client);
        List<AccountDto> accounts = clientService.getAccounts(client.id());
        model.addAttribute("accounts", accounts);
        model.addAttribute("cards", cardService.getClientCards(client.id()));
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        model.addAttribute("billingSystems", BillingSystem.values());
        return "accounts";
    }

    @PostMapping("/create")
    public String createAccount(@SessionAttribute("user") ClientDto clientDto,
                                @RequestParam("currencyId") Integer currencyId){
        accountService.createAccount(new AccountCreateDto(currencyId, clientDto.id()));
        return "redirect:/accounts";
    }

    @PostMapping("/p2p")
    public String p2p(@SessionAttribute("user") ClientDto clientDto,
                                Long cardId,
                                String cardNumber,
                                BigDecimal amountCard){
        cardService.P2P(cardId, cardNumber, amountCard);
        return "redirect:/accounts";
    }

    @PostMapping("/transfer")
    public String transfer(@SessionAttribute("user") ClientDto clientDto,
                                Long accountId,
                                String accountNumber,
                                BigDecimal amountAccount){
        accountService.moneyTransfer(accountId, accountNumber, amountAccount);
        return "redirect:/accounts";
    }

    @PostMapping("/createCard")
    public String createCard(CardCreateDto cardCreateDto){
        cardService.createCard(cardCreateDto.account(), cardCreateDto);
        return "redirect:/accounts";
    }
}
