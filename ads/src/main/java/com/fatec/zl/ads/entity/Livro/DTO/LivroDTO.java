package com.fatec.zl.ads.entity.Livro.DTO;

import java.util.List;

public record LivroDTO(String titulo, Float preco, String categoria, String resumo, Integer paginas, String dataPublicacao, String isbn, String status, Integer quantidade, String formato, List<Integer> listaDeAutores, Integer editora) {

}
