package com.fatec.zl.ads.service;

import org.springframework.stereotype.Service;

import com.fatec.zl.ads.entity.Editora.Editora;
import com.fatec.zl.ads.entity.Editora.DTO.EditoraCadastroDTO;
import com.fatec.zl.ads.repository.EditoraRepository;

@Service
public class EditoraService {
    private final EditoraRepository editoraRepository;

    public EditoraService(EditoraRepository editoraRepository){
        this.editoraRepository = editoraRepository;
    }

    public Editora cadastrarEditora(EditoraCadastroDTO dto){
        Editora novaEditora = new Editora();
        novaEditora.setNome(dto.nome());
        novaEditora.setEmail(dto.email());
        novaEditora.setCnpj(dto.cnpj());
        novaEditora.setTelefone(dto.telefone());
        return editoraRepository.save(novaEditora);
    }
}
