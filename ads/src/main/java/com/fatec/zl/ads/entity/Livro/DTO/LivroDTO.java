package com.fatec.zl.ads.entity.Livro.DTO;

import jakarta.validation.constraints.NotBlank;

public record LivroDTO(@NotBlank(message = "Título obrigatório") String titulo, Float preco, String categoria, String resumo, Integer paginas, String dataPublicacao, String isbn, String status, Integer quantidade, String formato) {

}
