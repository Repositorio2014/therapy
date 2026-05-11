package br.com.therapy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/therapy")
public class TherapyController {

    @GetMapping
    public String home() {
        return "Bem-vindo à API da clínica!";
    }
}

