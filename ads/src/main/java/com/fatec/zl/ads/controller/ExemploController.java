package com.fatec.zl.ads.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/*

Controller de exemplo

*/


@Controller
@RequestMapping("/exemplo")
public class ExemploController {
    @GetMapping("/teste")
    public String testarThymeleaf(@RequestParam("valor") String valor, Model model) {
        model.addAttribute("valor", valor);
        return "teste";
    }
    
}
