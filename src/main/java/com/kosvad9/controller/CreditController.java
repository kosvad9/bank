package com.kosvad9.controller;

import com.kosvad9.database.entity.Client;
import com.kosvad9.dto.ClientDto;
import com.kosvad9.service.ClientService;
import com.kosvad9.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;

@RequestMapping("/credits")
@Controller
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;
    private final ClientService clientService;

    @GetMapping
    public String credits(Model model, @SessionAttribute("user") ClientDto clientDto){
        model.addAttribute("credits", creditService.getCredits(clientDto.id()));
        model.addAttribute("accounts", clientService.getAccounts(clientDto.id()));
        return "credits";
    }

    @PostMapping("/pay")
    public String pay(Long creditId, Long accountId, BigDecimal sum){
        creditService.pay(creditId, accountId, sum);
        return "redirect:/credits";
    }
}
