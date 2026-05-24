package com.fatec.zl.ads.controller;

public class SistemaPagamento {

    public Double calcularDescontoPix (Double valor, int porcentagem) {
        return valor - porcentagem / 100 * valor;
    }

    public Double calcularDescontoCartao (Double valor, int porcentagem, int qteParcelas) {
        return valor - porcentagem / 100 * valor;
    }
    
}
