package com.fatec.zl.ads.entity.Pedido.Mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fatec.zl.ads.entity.ItemPedido.ItemPedido;
import com.fatec.zl.ads.entity.ItemPedido.DTO.ItemPedidoResponseDTO;
import com.fatec.zl.ads.entity.Pedido.Pedido;
import com.fatec.zl.ads.entity.Pedido.DTO.PedidoResponseDTO;

@Component
public class PedidoResponseMapper {
    public PedidoResponseDTO toDTO(Pedido entity){
        return new PedidoResponseDTO(
            entity.getId(),
            entity.getCliente().getId(),
            montarItens(entity.getItensPedido())
        );
    }

    private List<ItemPedidoResponseDTO> montarItens(List<ItemPedido> itensDoPedido){
        return itensDoPedido.stream().map(i  -> {
            return new ItemPedidoResponseDTO(
                i.getLivro().getId(),
                i.getQuantidade()
            );
        }).toList();
    }
}
