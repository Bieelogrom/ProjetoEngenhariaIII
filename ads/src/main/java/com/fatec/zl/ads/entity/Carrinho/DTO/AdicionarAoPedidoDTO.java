package com.fatec.zl.ads.entity.Carrinho.DTO;

import java.util.List;

import com.fatec.zl.ads.entity.ItemPedido.DTO.ItemPedidoDTO;

import jakarta.validation.constraints.NotNull;

public record AdicionarAoPedidoDTO(Integer idPedido, @NotNull Integer idCliente, List<ItemPedidoDTO> listaDeItens) {

}
