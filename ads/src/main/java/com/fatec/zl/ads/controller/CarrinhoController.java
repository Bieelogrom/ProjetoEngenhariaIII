package com.fatec.zl.ads.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fatec.zl.ads.entity.Carrinho.Carrinho;
import com.fatec.zl.ads.service.CarrinhoService;
import com.fatec.zl.ads.repository.CarrinhoRepository;

@Controller
@RequestMapping("/carrinhos")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;
    private final CarrinhoRepository carrinhoRepository;

    public CarrinhoController (CarrinhoService carrinhoService, CarrinhoRepository carrinhoRepository) {
        this.carrinhoService = carrinhoService;
        this.carrinhoRepository = carrinhoRepository;
    }

@GetMapping("/carrinho/visualizar/{idCarrinho}")
public ResponseEntity<?> visualizarCarrinhoAntes(@PathVariable String idCarrinho) {
    Carrinho carrinho = carrinhoRepository.findById(idCarrinho).orElse(null);
    if (carrinho == null || carrinho.getItens().isEmpty()) {
        return ResponseEntity.badRequest().body("O carrinho está vazio!");
    }
    return ResponseEntity.ok(carrinho.getItens());
}



    
}
