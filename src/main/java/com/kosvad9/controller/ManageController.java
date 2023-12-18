package com.kosvad9.controller;

import com.kosvad9.dto.ApplicationCreateDto;
import com.kosvad9.dto.ClientDto;
import com.kosvad9.service.ApplicationService;
import com.kosvad9.service.ClientService;
import com.kosvad9.service.CreditProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequestMapping("/manage")
@Controller
@RequiredArgsConstructor
public class ManageController {
    private final ApplicationService applicationService;

    @GetMapping
    public String creditApplications(Model model){
        model.addAttribute("creditApplications", applicationService.getFirstPageApplications());
        return "manage";
    }

    @PostMapping("/approve")
    public String approveApplication(Long applicationId){
        applicationService.approveApplication(applicationId);
        return "redirect:/manage";
    }

    @PostMapping("/reject")
    public String rejectApplication(Long applicationId, String reason){
        applicationService.rejectApplication(applicationId, reason);
        return "redirect:/manage";
    }
}
