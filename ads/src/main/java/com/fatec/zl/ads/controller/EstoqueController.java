package com.fatec.zl.ads.controller;

import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.repository.LivroRepository;

public class EstoqueController {

    private final LivroRepository livroRepository;

    public EstoqueController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
    
    public String atualizarEstoque (Livro livro, int qtePedida) {
        int novoEstoque = livro.getQteEstoque() - qtePedida;
        livro.setQteEstoque(novoEstoque);
        livroRepository.save(livro);
        if (novoEstoque <= 2) {
            return "Alerta: o livro " + livro.getNome()+ "atingiu o limite minimo!";
        } else {
            return null;
        }
    }
}
