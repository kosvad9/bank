package com.kosvad9.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String error(Model model, Exception exception){
        model.addAttribute("message", exception.toString());
        return "error";
    }
}
