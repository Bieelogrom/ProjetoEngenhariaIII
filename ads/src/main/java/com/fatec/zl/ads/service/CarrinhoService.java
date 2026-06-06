package com.fatec.zl.ads.service;

import org.springframework.stereotype.Service;
import com.fatec.zl.ads.repository.CarrinhoRepository;

@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;

    public CarrinhoService (CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

}
