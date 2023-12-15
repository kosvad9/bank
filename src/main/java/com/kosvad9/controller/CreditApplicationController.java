package com.kosvad9.controller;

import com.kosvad9.dto.ApplicationCreateDto;
import com.kosvad9.dto.ClientDto;
import com.kosvad9.service.ApplicationService;
import com.kosvad9.service.ClientService;
import com.kosvad9.service.CreditProgramService;
import com.kosvad9.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;

@RequestMapping("/creditApplications")
@Controller
@RequiredArgsConstructor
public class CreditApplicationController {
    private final ApplicationService applicationService;
    private final ClientService clientService;
    private final CreditProgramService creditProgramService;

    @GetMapping
    public String creditApplications(Model model, @SessionAttribute("user") ClientDto clientDto){
        model.addAttribute("creditApplications", applicationService.getAllApplications(clientDto.id()));
        model.addAttribute("creditPrograms", creditProgramService.getAllCreditProgram());
        return "creditApplications";
    }

    @PostMapping("/create")
    public String createApplication(ApplicationCreateDto applicationCreateDto,
                                    @SessionAttribute("user") ClientDto clientDto){
        applicationService.createApplication(applicationCreateDto, clientDto.id());
        return "redirect:/creditApplications";
    }
}
