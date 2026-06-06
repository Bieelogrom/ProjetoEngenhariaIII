package com.fatec.zl.ads.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.entity.Livro.DTO.LivroDTO;
import com.fatec.zl.ads.service.LivroService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/livro")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Livro>> buscarLivro(@RequestParam String titulo) {
        List<Livro> livros = service.buscarLivroPorTitulo(titulo);
        return ResponseEntity.ok(livros);
    }
    

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCapaDura(@RequestBody LivroDTO livroDTO) {
        Livro novoLivro = service.cadastrarLivro(livroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro.getTitulo()+" cadastrado com sucesso!");
    }
    

    @GetMapping("/listar")
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> listaDeLivros = service.listarTodosLivros();
        return ResponseEntity.ok(listaDeLivros);
    }
}
