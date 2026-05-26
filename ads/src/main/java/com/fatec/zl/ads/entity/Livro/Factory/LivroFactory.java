package com.fatec.zl.ads.entity.Livro.Factory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fatec.zl.ads.entity.Autor.Autor;
import com.fatec.zl.ads.entity.Autor.Exception.AutorNaoEncontradoException;
import com.fatec.zl.ads.entity.Editora.Editora;
import com.fatec.zl.ads.entity.Editora.Exception.EditoraNaoEncontradaException;
import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.entity.Livro.LivroBrochura;
import com.fatec.zl.ads.entity.Livro.LivroCapaDura;
import com.fatec.zl.ads.entity.Livro.LivroDigital;
import com.fatec.zl.ads.entity.Livro.LivroFisico;
import com.fatec.zl.ads.entity.Livro.StatusLivro;
import com.fatec.zl.ads.entity.Livro.DTO.LivroDTO;
import com.fatec.zl.ads.entity.Livro.Exception.FormatoNaoReconhecidoException;
import com.fatec.zl.ads.repository.AutorRepository;
import com.fatec.zl.ads.repository.EditoraRepository;

@Component
public class LivroFactory {
    private final AutorRepository autorRepository;
    private final EditoraRepository editoraRepository;

    public LivroFactory(AutorRepository autorRepository, EditoraRepository editoraRepository) {
        this.autorRepository = autorRepository;
        this.editoraRepository = editoraRepository;
    }

    public Livro fabricarLivro(LivroDTO dto){
        Livro livro;

        switch(dto.formato().toUpperCase()){
            case "DIGITAL":
                livro = new LivroDigital();
                break;
            case "BROCHURA":
                livro = new LivroBrochura();
                ((LivroFisico) livro).setQuantidade(dto.quantidade());
                break;
            case "CAPA_DURA":
                livro = new LivroCapaDura();
                ((LivroFisico) livro).setQuantidade(dto.quantidade());
                break;
            default:
                throw new FormatoNaoReconhecidoException();
        }

        livro.setTitulo(dto.titulo());
        livro.setPreco(BigDecimal.valueOf(dto.preco()));
        livro.setCategorias(dto.categoria());
        livro.setResumo(dto.resumo());
        livro.setDataPublicacao(formatarData(dto.dataPublicacao()));
        livro.setIsbn(dto.isbn());
        livro.setStatus(StatusLivro.valueOf(dto.status()));
        livro.setAutores(buscarAutores(dto.listaDeAutores()));
        livro.setEditora(buscarEditora(dto.editora()));

        return livro;
    }

    private Editora buscarEditora(String id){
        return editoraRepository.findById(id).orElseThrow(() -> new EditoraNaoEncontradaException("Editora não encontrada!"));
    }

    private List<Autor> buscarAutores(List<String> ids){
        List<Autor> listaDeAutores = new ArrayList<>();

        for(String id : ids){
            Autor autor = autorRepository.findById(id).orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado!"));
            listaDeAutores.add(autor);
        }

        return listaDeAutores;
    }

    private static LocalDate formatarData(String data){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data, formato);
        return dataConvertida;
    }
}
