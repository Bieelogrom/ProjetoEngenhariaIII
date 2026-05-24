package com.fatec.zl.ads.entity.Pedido;

public record PedidoDTORequest (String idCarrinho, String enderecoEntrega, String formaPagamento, Integer qteParcelas) {
}
