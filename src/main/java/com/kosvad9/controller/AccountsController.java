package com.kosvad9.controller;

import com.kosvad9.dto.ClientDto;
import com.kosvad9.service.AccountService;
import com.kosvad9.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@RequestMapping("/accounts")
@Controller
@SessionAttributes({"user"})
@RequiredArgsConstructor
public class AccountsController {
    private final ClientService clientService;
    private final AccountService accountService;
    @GetMapping
    public String getMain(Model model){
        ClientDto client = clientService.getClient(1L);
        model.addAttribute("user", client);
        model.addAttribute("accounts", clientService.getAccounts(1L));
        return "accounts";
    }
}
