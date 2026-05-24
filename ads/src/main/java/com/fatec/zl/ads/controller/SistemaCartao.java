package com.fatec.zl.ads.controller;

import java.util.Random;

public class SistemaCartao {

    Random random = new Random();

    public boolean pagarAVista (Double valor) {
        return random.nextBoolean();
    }

    public boolean pagarParcelado (Double valor, int qteParcelas) {
        return random.nextBoolean();
    }

}
