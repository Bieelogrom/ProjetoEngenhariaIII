package com.fatec.zl.ads.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.zl.ads.entity.Carrinho.Carrinho;
import com.fatec.zl.ads.entity.Carrinho.DTO.AdicionarAoCarrinhoDTO;
import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.ItemCarrinho.ItemCarrinho;
import com.fatec.zl.ads.entity.ItemCarrinho.DTO.ItemCarrinhoDTO;
import com.fatec.zl.ads.entity.ItemPedido.ItemPedido;
import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.repository.CarrinhoRepository;
import com.fatec.zl.ads.repository.ClienteRepository;
import com.fatec.zl.ads.repository.LivroRepository;

import jakarta.transaction.Transactional;

@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final LivroRepository livroRepository;
    private final ClienteRepository clienteRepository;

    public CarrinhoService (CarrinhoRepository carrinhoRepository, LivroRepository livroRepository, ClienteRepository clienteRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.livroRepository = livroRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public AdicionarAoCarrinhoDTO adicionarAoCarrinho(AdicionarAoCarrinhoDTO dto){
        Carrinho carrinho = carrinhoRepository.findById(dto.idCarrinho()).orElseGet(() -> {
            Carrinho novoCarrinho = new Carrinho();
            return carrinhoRepository.save(novoCarrinho);
        });
        List<ItemCarrinho> listaDeItens = montarItensDoCarrinho(dto);
        carrinho.setItens(listaDeItens);
        carrinho.setCliente(montarDonoDoCarrinho(dto.idCliente()));
        carrinhoRepository.save(carrinho);
        return dto;
    }

    private List<ItemCarrinho> montarItensDoCarrinho(AdicionarAoCarrinhoDTO dto){
        List<ItemCarrinho> listaDeItens = new ArrayList<>();
        for(ItemCarrinhoDTO item : dto.listaDeItens()){
            ItemCarrinho novoItemDoCarrinho = new ItemCarrinho();
            Livro livro = livroRepository.findById(item.idLivro()).orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
            novoItemDoCarrinho.setLivro(livro);
            novoItemDoCarrinho.setQuantidade(item.quantidade());
            listaDeItens.add(novoItemDoCarrinho);
        }
        return listaDeItens;
    }

    private Cliente montarDonoDoCarrinho(Integer idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        return cliente;
    }

}
