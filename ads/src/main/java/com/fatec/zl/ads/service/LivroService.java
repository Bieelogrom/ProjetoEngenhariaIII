package com.fatec.zl.ads.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.entity.Livro.DTO.LivroDTO;
import com.fatec.zl.ads.entity.Livro.Factory.LivroFactory;
import com.fatec.zl.ads.repository.LivroRepository;

@Service
public class LivroService {
    private final LivroRepository repository;
    private final LivroFactory factory;

    public LivroService(LivroRepository repository, LivroFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public List<Livro> buscarLivroPorTitulo(String titulo){
        List<Livro> livrosEncontrados = repository.findByTituloContainingIgnoreCase(titulo);
        return livrosEncontrados;
    }

    public List<Livro> listarTodosLivros(){
        List<Livro> listaDeLivros = repository.findAll();
        return listaDeLivros;
    }

    public Livro cadastrarLivro(LivroDTO dto){
        Livro novoLivro = factory.fabricarLivro(dto);
        return repository.save(novoLivro);
    }
}
