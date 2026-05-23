package com.fatec.zl.ads.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.entity.Livro.LivroDTORequest;
import com.fatec.zl.ads.repository.LivroRepository;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro cadastrarLivro(LivroDTORequest livro){
        Livro novoLivro = new Livro();
        novoLivro.setNome(livro.nome());
        novoLivro.setDescricao(livro.descricao());
        novoLivro.setPaginas(livro.paginas());
        novoLivro.setDataPublicacao(formatarData(livro.dataPublicacao()));
        return livroRepository.save(novoLivro);
    }

    public List<Livro> listarLivros(){
        List<Livro> listaDeLivros = livroRepository.findAll();
        return listaDeLivros;
    }

    private LocalDate formatarData(String data){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data, formato);
        return dataConvertida;
    }
}
