package com.fatec.zl.ads.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.entity.Livro.LivroDTORequest;
import com.fatec.zl.ads.service.LivroService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/livros")
public class ControlePedido {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCliente(@RequestBody LivroDTORequest livroDto) {
        Livro livroCadastrado = livroService.cadastrarLivro(livroDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCadastrado.getNome()+" cadastrado com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> listaDeLivros = livroService.listarLivros();
        return ResponseEntity.ok(listaDeLivros);
    }

    public boolean pagarAVista(Double valor) {
        return true;
    }

    public boolean pagarParcelado(Double valor, int qteParcela) {
        return false;
    }

    public boolean pagarPix(Double valor) {
        return true;
    }

    public void informarCarrinhoVazio() {
        exibirMensagemCarrinhoVazio();
    }

    private void exibirMensagemCarrinhoVazio() {

    }
    
}
