package com.fatec.zl.ads.entity.Livro.Factory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.entity.Livro.LivroBrochura;
import com.fatec.zl.ads.entity.Livro.LivroCapaDura;
import com.fatec.zl.ads.entity.Livro.LivroDigital;
import com.fatec.zl.ads.entity.Livro.LivroFisico;
import com.fatec.zl.ads.entity.Livro.StatusLivro;
import com.fatec.zl.ads.entity.Livro.DTO.LivroDTO;
import com.fatec.zl.ads.entity.Livro.Exception.FormatoNaoReconhecidoException;

@Component
public class LivroFactory {
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

        return livro;
    }

    private LocalDate formatarData(String data){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data, formato);
        return dataConvertida;
    }
}
