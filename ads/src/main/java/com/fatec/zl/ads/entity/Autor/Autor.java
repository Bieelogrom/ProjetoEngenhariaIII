package com.fatec.zl.ads.entity.Autor;

import java.time.LocalDate;
import java.util.List;

import com.fatec.zl.ads.entity.Livro.Livro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "autor")
@NoArgsConstructor
@Getter
@Setter
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 120, nullable = false)
    private String nome;
    @Column(nullable = false)
    private LocalDate dataNasc;
    @Column(nullable = false)
    private String nacionalidade;
    @ManyToMany(mappedBy = "autores")
    private List<Livro> livros;
}
