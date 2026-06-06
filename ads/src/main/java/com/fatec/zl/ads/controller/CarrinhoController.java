package com.fatec.zl.ads.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fatec.zl.ads.entity.Carrinho.Carrinho;
import com.fatec.zl.ads.entity.Carrinho.DTO.AdicionarAoCarrinhoDTO;
import com.fatec.zl.ads.entity.Carrinho.DTO.AdicionarAoCarrinhoResponseDTO;
import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.ItemCarrinho.ItemCarrinho;
import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.service.CarrinhoService;

import jakarta.validation.Valid;

import com.fatec.zl.ads.repository.CarrinhoRepository;
import com.fatec.zl.ads.repository.ClienteRepository;
import com.fatec.zl.ads.repository.LivroRepository;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;
    private final CarrinhoRepository carrinhoRepository;
    private final LivroRepository livroRepository;
    private final ClienteRepository clienteRepository;

    public CarrinhoController (CarrinhoService carrinhoService, CarrinhoRepository carrinhoRepository, LivroRepository livroRepository, ClienteRepository clienteRepository) {
        this.carrinhoService = carrinhoService;
        this.carrinhoRepository = carrinhoRepository;
        this.livroRepository = livroRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/carrinho/visualizar/{idCarrinho}")
    public ResponseEntity<?> visualizarCarrinhoAntes(@PathVariable Integer idCarrinho) {
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho).orElse(null);
        if (carrinho == null || carrinho.getItens().isEmpty()) {
            return ResponseEntity.badRequest().body("O carrinho está vazio!");
        }
        return ResponseEntity.ok(carrinho.getItens());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<AdicionarAoCarrinhoDTO> adicionarAoCarrinho(@RequestBody @Valid AdicionarAoCarrinhoDTO dto) {
        AdicionarAoCarrinhoDTO dtoResponse = carrinhoService.adicionarAoCarrinho(dto);
        return ResponseEntity.ok(dtoResponse);
    }   

}
