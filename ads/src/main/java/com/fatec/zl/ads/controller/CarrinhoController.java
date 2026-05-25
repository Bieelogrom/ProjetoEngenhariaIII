package com.fatec.zl.ads.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fatec.zl.ads.entity.Carrinho.Carrinho;
import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.ItemCarrinho.ItemCarrinho;
import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.service.CarrinhoService;
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
    public ResponseEntity<?> visualizarCarrinhoAntes(@PathVariable String idCarrinho) {
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho).orElse(null);
        if (carrinho == null || carrinho.getItens().isEmpty()) {
            return ResponseEntity.badRequest().body("O carrinho está vazio!");
        }
        return ResponseEntity.ok(carrinho.getItens());
    }

    @PostMapping("/adicionar/{idCarrinho}/{idCliente}/{idLivro}/{quantidade}")
    public ResponseEntity<?> adicionarAoCarrinho(@PathVariable String idCarrinho, @PathVariable String idLivro, @PathVariable String idCliente, @PathVariable int quantidade) {
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(idCarrinho);
        Carrinho carrinho;
        if (carrinhoOptional.isPresent()) {
            carrinho = carrinhoOptional.get();
        } 
        else {
            carrinho = new Carrinho();
            carrinho.setId(idCarrinho);
            Cliente cliente = clienteRepository.findById(idCliente).get();
            carrinho.setCliente(cliente);
        }
        Livro livro = livroRepository.findById(idLivro).get();
        ItemCarrinho item = new ItemCarrinho();
        item.setQuantidade(quantidade);
        item.setLivro(livro);
        carrinho.getItens().add(item);
        carrinhoRepository.save(carrinho);
        return ResponseEntity.ok("{\"mensagem\": \"Livro adicionado ao carrinho com sucesso!\"}");
    }   

}
