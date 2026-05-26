package com.fatec.zl.ads.entity.Editora;

import java.util.List;

import com.fatec.zl.ads.entity.Livro.Livro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "editora")
@NoArgsConstructor
@Getter
@Setter
public class Editora {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 120, nullable = false)
    private String nome;
    @Column(length = 27, nullable = false)
    private String cnpj;
    @Column(length = 27, nullable = false)
    private String telefone;
    @Column(length = 120, nullable = false)
    private String email;
    @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL)
    private List<Livro> livros;
}
