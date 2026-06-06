package com.fatec.zl.ads.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.zl.ads.entity.ItemPedido.ItemPedido;
import com.fatec.zl.ads.entity.ItemPedido.DTO.ItemPedidoDTO;
import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.repository.ItemPedidoRepository;
import com.fatec.zl.ads.repository.LivroRepository;

@Service
public class ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;
    private final LivroRepository livroRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository, LivroRepository livroRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.livroRepository = livroRepository;
    }

    public BigDecimal montarTotalDoCarrinho(List<ItemPedidoDTO> listaDto) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemPedidoDTO item : listaDto) {
            Livro livro = livroRepository.findById(item.idLivro()).orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
            valorTotal = valorTotal.add(livro.calcularPrecoVenda().multiply(BigDecimal.valueOf(item.quantidade())));
        }
        return valorTotal;
    }

    
    public List<ItemPedido> montarItensDoCarrinho(List<ItemPedidoDTO> listaDto){
        List<ItemPedido> listaDeItens = new ArrayList<>();
        for(ItemPedidoDTO item : listaDto){
            ItemPedido novoItemDoCarrinho = new ItemPedido();
            Livro livro = livroRepository.findById(item.idLivro()).orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
            novoItemDoCarrinho.setLivro(livro);
            novoItemDoCarrinho.setQuantidade(item.quantidade());
            listaDeItens.add(novoItemDoCarrinho);
        }
        return listaDeItens;
    }

}
