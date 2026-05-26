package com.fatec.zl.ads;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fatec.zl.ads.entity.Autor.Autor;
import com.fatec.zl.ads.entity.Editora.Editora;
import com.fatec.zl.ads.repository.AutorRepository;
import com.fatec.zl.ads.repository.EditoraRepository;
import com.fatec.zl.ads.repository.LivroRepository;

@Component
public class Inicializador implements CommandLineRunner {
    private final EditoraRepository editoraRepository;
    private final AutorRepository autorRepository;
    public Inicializador(EditoraRepository editoraRepository, AutorRepository autorRepository, LivroRepository livroRepository) {
        this.editoraRepository = editoraRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        criarEditoras();
        criarAutores();
    }

    private void criarEditoras(){
        Editora editora1 = new Editora();
        editora1.setNome("Companhia das Letras");
        editora1.setCnpj("09.702.787/0001-08");
        editora1.setTelefone("(11) 98552-6977");
        editora1.setEmail("suporte@luisemartinmudancasme.com.br");
        editoraRepository.saveAll(List.of(editora1));
    }

    private void criarAutores(){
        Autor autor1 = new Autor();
        autor1.setNome("Anthony Elias Manoel Gonçalves");
        autor1.setDataNasc(formatarData("20/02/1962"));
        autor1.setNacionalidade("Brasileiro/a");
        autorRepository.saveAll(List.of(autor1));
    }

    private static LocalDate formatarData(String data){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data, formato);
        return dataConvertida;
    }
}
