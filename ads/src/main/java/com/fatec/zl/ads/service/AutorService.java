package com.fatec.zl.ads.service;

import org.springframework.stereotype.Service;

import com.fatec.zl.ads.entity.Autor.Autor;
import com.fatec.zl.ads.entity.Autor.DTO.AutorCadastroDTO;
import com.fatec.zl.ads.infra.utils.DateUtils;
import com.fatec.zl.ads.repository.AutorRepository;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public Autor cadastrarAutor(AutorCadastroDTO dto){
        Autor novoAutor = new Autor();
        novoAutor.setNome(dto.nome());
        novoAutor.setDataNasc(DateUtils.formatarData(dto.dataNasc()));
        novoAutor.setNacionalidade(dto.nacionalidade());
        return autorRepository.save(novoAutor);
    }
}
