package com.fatec.zl.ads.service;

import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {
    // private final CarrinhoRepository carrinhoRepository;
    // private final LivroRepository livroRepository;
    // private final ClienteRepository clienteRepository;

    // public CarrinhoService (CarrinhoRepository carrinhoRepository, LivroRepository livroRepository, ClienteRepository clienteRepository) {
    //     this.carrinhoRepository = carrinhoRepository;
    //     this.livroRepository = livroRepository;
    //     this.clienteRepository = clienteRepository;
    // }

    // @Transactional
    // public AdicionarAoCarrinhoDTO adicionarAoCarrinho(AdicionarAoCarrinhoDTO dto){
    //     Carrinho carrinho = carrinhoRepository.findById(dto.idCarrinho()).orElseGet(() -> {
    //         Carrinho novoCarrinho = new Carrinho();
    //         return carrinhoRepository.save(novoCarrinho);
    //     });
    //     List<ItemCarrinho> listaDeItens = montarItensDoCarrinho(dto);
    //     carrinho.setItens(listaDeItens);
    //     carrinho.setCliente(montarDonoDoCarrinho(dto.idCliente()));
    //     carrinhoRepository.save(carrinho);
    //     return dto;
    // }

    // private List<ItemCarrinho> montarItensDoCarrinho(AdicionarAoCarrinhoDTO dto){
    //     List<ItemCarrinho> listaDeItens = new ArrayList<>();
    //     for(ItemCarrinhoDTO item : dto.listaDeItens()){
    //         ItemCarrinho novoItemDoCarrinho = new ItemCarrinho();
    //         Livro livro = livroRepository.findById(item.idLivro()).orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
    //         novoItemDoCarrinho.setLivro(livro);
    //         novoItemDoCarrinho.setQuantidade(item.quantidade());
    //         listaDeItens.add(novoItemDoCarrinho);
    //     }
    //     return listaDeItens;
    // }

    // private Cliente montarDonoDoCarrinho(Integer idCliente){
    //     Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    //     return cliente;
    // }

}
