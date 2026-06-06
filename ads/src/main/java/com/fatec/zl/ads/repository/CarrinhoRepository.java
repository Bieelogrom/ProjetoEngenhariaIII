package com.fatec.zl.ads.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.zl.ads.entity.Carrinho.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

}
