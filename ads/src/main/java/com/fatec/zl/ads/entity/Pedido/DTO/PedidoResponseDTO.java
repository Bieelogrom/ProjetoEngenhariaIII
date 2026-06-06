package com.fatec.zl.ads.entity.Pedido.DTO;

import java.util.List;

import com.fatec.zl.ads.entity.ItemPedido.DTO.ItemPedidoResponseDTO;

public record PedidoResponseDTO(Integer idPedido,Integer idCliente,List<ItemPedidoResponseDTO> listaDeItens) {

}
