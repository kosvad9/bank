package com.kosvad9.controller;

import com.kosvad9.dto.AccountCreateDto;
import com.kosvad9.dto.ClientDto;
import com.kosvad9.service.AccountService;
import com.kosvad9.service.ClientService;
import com.kosvad9.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/accounts")
@Controller
@SessionAttributes({"user"})
@RequiredArgsConstructor
public class AccountsController {
    private final ClientService clientService;
    private final AccountService accountService;
    private final CurrencyService currencyService;
    @GetMapping
    public String allAccounts(Model model, Principal principal){
        ClientDto client = clientService.getClient(Long.parseLong(principal.getName()));
        model.addAttribute("user", client);
        model.addAttribute("accounts", clientService.getAccounts(client.id()));
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        return "accounts";
    }

    @PostMapping("/create")
    public String createAccount(@SessionAttribute("user") ClientDto clientDto, @RequestParam("currencyId") Integer currencyId){
        accountService.createAccount(new AccountCreateDto(currencyId, clientDto.id()));
        return "redirect:/accounts";
    }
}
