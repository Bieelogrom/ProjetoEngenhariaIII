package com.fatec.zl.ads.entity.ItemPedido.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoDTO(@NotNull Integer idLivro, @Positive Integer quantidade) {

}
