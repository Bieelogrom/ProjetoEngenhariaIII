package com.fatec.zl.ads.entity.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.zl.ads.entity.Autor.Autor;
import com.fatec.zl.ads.entity.Editora.Editora;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "livros")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_livro", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Getter
@Setter
public abstract class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 120, nullable = false)
    private String titulo;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
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
    @Column(nullable = false)
    private int qteEstoque;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusLivro status;
    @Column(name = "percentual_desconto", nullable = false, precision = 5, scale = 4)
    private BigDecimal percentualDesconto = BigDecimal.ZERO;
    @ManyToMany
    @JoinTable(
        name = "livro_autor",
        joinColumns = @JoinColumn(name = "livro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @JsonIgnoreProperties("livros")
    private List<Autor> autores;
    @ManyToOne
    @JoinColumn(name = "editora_id", nullable = false)
    @JsonIgnoreProperties("livros")
    private Editora editora;
}
