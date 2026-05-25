package com.fatec.zl.ads.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.entity.Livro.LivroCapaDura;
import com.fatec.zl.ads.entity.Livro.LivroFisicoDTORequest;
import com.fatec.zl.ads.entity.Livro.StatusLivro;
import com.fatec.zl.ads.repository.LivroRepository;

@Service
public class LivroService {
    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public List<Livro> buscarLivroPorTitulo(String titulo){
        List<Livro> livrosEncontrados = repository.findByTituloContainingIgnoreCase(titulo);
        return livrosEncontrados;
    }

    public List<Livro> listarTodosLivros(){
        List<Livro> listaDeLivros = repository.findAll();
        return listaDeLivros;
    }

    public Livro cadastrarCapaDura(LivroFisicoDTORequest livroDTO){
        LivroCapaDura novoLivroFisico = new LivroCapaDura();
        novoLivroFisico.setTitulo(livroDTO.titulo());
        novoLivroFisico.setPreco(BigDecimal.valueOf(livroDTO.preco()));
        novoLivroFisico.setCategorias(livroDTO.categoria());
        novoLivroFisico.setResumo(livroDTO.resumo());
        novoLivroFisico.setDataPublicacao(formatarData(livroDTO.dataPublicacao()));
        novoLivroFisico.setIsbn(livroDTO.isbn());
        novoLivroFisico.setStatus(StatusLivro.valueOf(livroDTO.status()));
        novoLivroFisico.setQuantidade(livroDTO.quantidade());
        return repository.save(novoLivroFisico);
    }

    private LocalDate formatarData(String data){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data, formato);
        return dataConvertida;
    }
}
