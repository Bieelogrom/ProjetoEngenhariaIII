package com.fatec.zl.ads.entity.Livro;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 120)
    private String titulo;
    @Column(nullable = false)
    private Float preco;
    @Column(nullable = false)
    private String categorias; 
    @Column(length = 220, nullable = false)
    private String resumo;
    @Column(nullable = false)
    private int paginas;
    @Column(nullable = false)
    private LocalDate dataPublicacao;
    @Column(length = 32, nullable = false)
    private String isbn;


    public Livro(String titulo, Float preco, String categorias, String resumo, int paginas, LocalDate dataPublicacao,
            String isbn) {
        this.titulo = titulo;
        this.preco = preco;
        this.categorias = categorias;
        this.resumo = resumo;
        this.paginas = paginas;
        this.dataPublicacao = dataPublicacao;
        this.isbn = isbn;
    }
}
