package com.kosvad9.controller;

import com.kosvad9.dto.ClientCreateDto;
import com.kosvad9.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class LoginController {
    private final ClientService clientService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute ClientCreateDto clientCreateDto){
        clientService.registration(clientCreateDto);
        return "redirect:/login";
    }
}
