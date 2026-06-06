package com.fatec.zl.ads.service;

import org.springframework.stereotype.Service;

import com.fatec.zl.ads.repository.ItemPedidoRepository;


@Service
public class ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService (ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

}
