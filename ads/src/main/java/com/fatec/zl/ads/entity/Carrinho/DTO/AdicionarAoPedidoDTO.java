package com.fatec.zl.ads.entity.Carrinho.DTO;

import java.util.List;

import com.fatec.zl.ads.entity.ItemCarrinho.DTO.ItemCarrinhoDTO;

import jakarta.validation.constraints.NotNull;

public record AdicionarAoPedidoDTO(Integer idPedido, @NotNull Integer idCliente, List<ItemCarrinhoDTO> listaDeItens) {

}
