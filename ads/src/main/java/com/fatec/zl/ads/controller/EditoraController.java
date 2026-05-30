package com.fatec.zl.ads.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.zl.ads.entity.Editora.Editora;
import com.fatec.zl.ads.entity.Editora.DTO.EditoraCadastroDTO;
import com.fatec.zl.ads.service.EditoraService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/editora")
public class EditoraController {
    private final EditoraService editoraService;

    public EditoraController(EditoraService editoraService){
        this.editoraService = editoraService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarEditora(@RequestBody EditoraCadastroDTO dto) {
        Editora novaEditora = editoraService.cadastrarEditora(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEditora.getNome()+" cadastrada com sucesso!");
    }
    
}
