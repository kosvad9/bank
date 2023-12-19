package com.kosvad9.controller;

import com.kosvad9.dto.ClientCreateDto;
import com.kosvad9.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String registration(Model model, @ModelAttribute("client") ClientCreateDto clientCreateDto){
        model.addAttribute("client", clientCreateDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute @Validated ClientCreateDto clientCreateDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("client", clientCreateDto);
            return "redirect:/login/registration";
        }
        clientService.registration(clientCreateDto);
        return "redirect:/login";
    }
}
