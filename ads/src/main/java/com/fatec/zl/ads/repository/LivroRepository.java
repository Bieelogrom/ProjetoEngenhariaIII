package com.fatec.zl.ads.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.zl.ads.entity.Livro.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    public List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
