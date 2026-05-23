package com.fatec.zl.ads.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "livros")
@Table(name = "livros")
@NoArgsConstructor
@Getter
@Setter
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 120)
    private String nome;
    @Column(nullable = false)
    private int paginas;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Date dataPublicacao;

    public Livro(String nome, int paginas, String descricao, Date dataPublicacao) {
        this.nome = nome;
        this.paginas = paginas;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
    }
}
