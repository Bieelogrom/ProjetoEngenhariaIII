package com.fatec.zl.ads.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;

@Controller
public class SistemaFrete {

    public BigDecimal calcularFrete(String endereco) {
        return BigDecimal.valueOf(18);
    }
}
