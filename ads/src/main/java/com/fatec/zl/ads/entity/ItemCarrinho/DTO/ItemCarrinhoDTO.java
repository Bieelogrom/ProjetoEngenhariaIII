package com.fatec.zl.ads.entity.ItemCarrinho.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemCarrinhoDTO(@NotNull Integer idLivro, @Positive Integer quantidade) {

}
