package com.fatec.zl.ads.entity.Livro;

public record LivroFisicoDTORequest(String titulo, Float preco, String categoria, String resumo, Integer paginas, String dataPublicacao, String isbn, String status, Integer quantidade) {

}
