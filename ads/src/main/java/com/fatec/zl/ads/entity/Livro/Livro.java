package com.fatec.zl.ads.entity.Livro;

import java.time.LocalDate;

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
    private LocalDate dataPublicacao;
    @Column(nullable = false)
    private int qteEstoque;
    @Column(nullable = false)
    private double preco;


    public Livro(String nome, int paginas, String descricao, LocalDate dataPublicacao, int qteEstoque, Double preco) {
        this.nome = nome;
        this.paginas = paginas;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.qteEstoque = qteEstoque;
        this.preco = preco;
    }
}
