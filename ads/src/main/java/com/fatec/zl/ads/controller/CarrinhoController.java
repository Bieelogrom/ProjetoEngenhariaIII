package com.fatec.zl.ads.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    // private final CarrinhoService carrinhoService;
    // private final CarrinhoRepository carrinhoRepository;
    // // private final LivroRepository livroRepository;
    // private final ClienteRepository clienteRepository;

    // public CarrinhoController (CarrinhoService carrinhoService, CarrinhoRepository carrinhoRepository, LivroRepository livroRepository, ClienteRepository clienteRepository) {
    //     this.carrinhoService = carrinhoService;
    //     this.carrinhoRepository = carrinhoRepository;
    //     this.livroRepository = livroRepository;
    //     this.clienteRepository = clienteRepository;
    // }

    // @GetMapping("/carrinho/visualizar/{idCarrinho}")
    // public ResponseEntity<?> visualizarCarrinhoAntes(@PathVariable Integer idCarrinho) {
    //     Carrinho carrinho = carrinhoRepository.findById(idCarrinho).orElse(null);
    //     if (carrinho == null || carrinho.getItens().isEmpty()) {
    //         return ResponseEntity.badRequest().body("O carrinho está vazio!");
    //     }
    //     return ResponseEntity.ok(carrinho.getItens());
    // }

    // @PostMapping("/adicionar")
    // public ResponseEntity<AdicionarAoCarrinhoDTO> adicionarAoCarrinho(@RequestBody @Valid AdicionarAoCarrinhoDTO dto) {
    //     AdicionarAoCarrinhoDTO dtoResponse = carrinhoService.adicionarAoCarrinho(dto);
    //     return ResponseEntity.ok(dtoResponse);
    // }   

}
