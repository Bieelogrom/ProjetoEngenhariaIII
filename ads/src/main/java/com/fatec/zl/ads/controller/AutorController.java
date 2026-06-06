package com.fatec.zl.ads.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.zl.ads.entity.Autor.Autor;
import com.fatec.zl.ads.entity.Autor.DTO.AutorCadastroDTO;
import com.fatec.zl.ads.service.AutorService;

@RestController
@RequestMapping("/autor")
public class AutorController {
    private final AutorService autorService;

    public AutorController(AutorService autorService){
        this.autorService = autorService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarAutor(@RequestBody AutorCadastroDTO dto){
        Autor novoAutor = autorService.cadastrarAutor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAutor.getNome()+" cadastrado com sucesso!");
    }
}
