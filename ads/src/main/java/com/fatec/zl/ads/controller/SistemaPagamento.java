package com.fatec.zl.ads.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;

@Controller
public class SistemaPagamento {

    public BigDecimal calcularValorFinal(BigDecimal valorPedido,String formaPagamento,Integer parcelas) {

        switch (formaPagamento) {

            case "PIX":
                return aplicarDesconto(valorPedido, 8);

            case "CARTAO_CREDITO":

                if (parcelas == null || parcelas < 1 || parcelas > 3) {
                    throw new IllegalArgumentException(
                        "Cartão permite de 1 a 3 parcelas");
                }

                if (parcelas == 1) {
                    return aplicarDesconto(valorPedido, 3);
                }
                return valorPedido;

            default:
                throw new IllegalArgumentException("Forma de pagamento inválida");
        }
    }

    private BigDecimal aplicarDesconto(BigDecimal valor, int porcentagem) {
        BigDecimal desconto = valor.multiply(BigDecimal.valueOf(porcentagem)).divide(BigDecimal.valueOf(100));
        return valor.subtract(desconto);
    }

}
