package com.fatec.zl.ads.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.zl.ads.controller.SistemaFrete;
import com.fatec.zl.ads.entity.Carrinho.DTO.AdicionarAoPedidoDTO;
import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.ItemCarrinho.DTO.ItemCarrinhoDTO;
import com.fatec.zl.ads.entity.ItemPedido.ItemPedido;
import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.entity.Pedido.Pedido;
import com.fatec.zl.ads.repository.CarrinhoRepository;
import com.fatec.zl.ads.repository.ClienteRepository;
import com.fatec.zl.ads.repository.LivroRepository;
import com.fatec.zl.ads.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final LivroRepository livroRepository;
    private final ClienteRepository clienteRepository;
    SistemaFrete sf = new SistemaFrete();

    public PedidoService(PedidoRepository pedidoRepository, CarrinhoRepository carrinhoRepository, LivroRepository livroRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.livroRepository = livroRepository;
        this.clienteRepository = clienteRepository;
    }

    // public Pedido exibirPedido (String idCarrinho) {
    //     Carrinho carrinho = carrinhoRepository.findById(idCarrinho).orElseThrow(() -> new RuntimeException("Carrinho nao encontrado!"));
    //     if (carrinho.getItens().isEmpty()) {
    //         throw new RuntimeException("O carrinho esta vazio");
    //     }
    //     double valorPedido = 0;
    //     for(ItemCarrinho item: carrinho.getItens()) {
    //         valorPedido += item.getLivro().getPreco() * item.getQuantidade();
    //     }
    //     valorPedido += sf.calcularFrete(carrinho.getCliente().getEndereco());
    //     Pedido pedido = new Pedido();
    //     pedido.setCliente(carrinho.getCliente());
    //     pedido.setValorTotal(valorPedido);
    //     pedido.setStatusPedido("Em processamento");
    //     pedido.setDataPedido(LocalDate.now());
    //     return pedido;
    // }

    // public Pedido efetuarPedido (String idCarrinho, String endereco, String formaPagamento, int qteParcelas) {
    //     SistemaPagamento sp = new SistemaPagamento();
    //     EstoqueController ec = new EstoqueController(livroRepository);
    //     List<String> listaAlertas = new ArrayList<>();
    //     SistemaCartao sc = new SistemaCartao();
    //     Carrinho carrinho = carrinhoRepository.findById(idCarrinho).get();
    //     if (carrinho.getItens().isEmpty()) {
    //         throw new RuntimeException("O carrinho está vazio!");
    //     }
    //     Pedido pedido = new Pedido();
    //     pedido.setCliente(carrinho.getCliente());
    //     pedido.setDataPedido(LocalDate.now());
    //     pedido.setQteParcelas(qteParcelas);
    //     double valorPedido = 0;
    //     for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
    //         valorPedido += (itemCarrinho.getLivro().getPreco() * itemCarrinho.getQuantidade());
    //         String alerta = ec.atualizarEstoque(itemCarrinho.getLivro(), itemCarrinho.getQuantidade());
    //         if (alerta != null) {
    //             listaAlertas.add(alerta);
    //         }
    //     }
    //     double frete = sf.calcularFrete(endereco);
    //     valorPedido += frete;
    //     pedido.setStatusPedido("Pagamento pendente");
    //     if (formaPagamento.equals("Pix")) {
    //         valorPedido = sp.calcularDescontoPix(valorPedido, 8);
    //         SistemaBanco sb = new SistemaBanco();
    //         if (!sb.pagarPix(valorPedido)) {
    //                 throw new RuntimeException("Falha no pagamento!");
    //         }
    //         else {
    //             pedido.setStatusPedido("Confirmado");
    //         }
    //     } else if (formaPagamento.equals("Cartao")) {
    //         if (pedido.getQteParcelas() == 1) {
    //             valorPedido = sp.calcularDescontoCartao(valorPedido, 3, 1 );
    //             if (!sc.pagarAVista(valorPedido)){
    //                 throw new RuntimeException("Falha no pagamento!");
    //             } else {
    //                 pedido.setStatusPedido("Confirmado");
    //             }
    //         } else {
    //             if (!sc.pagarParcelado(valorPedido, pedido.getQteParcelas())) {
    //                 throw new RuntimeException("Falha no pagamento!");
    //             } else {
    //                 pedido.setStatusPedido("Confirmado");
    //             }
    //         }
    //     }
    //     pedido.setStatusPedido("Finalizado");                     
    //     if (!listaAlertas.isEmpty()) {
    //         String todosAlertas = String.join(" | ", listaAlertas);
    //         pedido.setStatusPedido(pedido.getStatusPedido() + " - " + todosAlertas);
    //     }
    //     pedido.setValorTotal(valorPedido);
    //     Pedido pedidoSalvo = pedidoRepository.save(pedido);
    //     carrinho.getItens().clear();
    //     carrinhoRepository.save(carrinho);
    //     return pedidoSalvo;
    // }
    
    @Transactional
    public AdicionarAoPedidoDTO adicionarNovoPedido(AdicionarAoPedidoDTO dto){
        Pedido pedido = pedidoRepository.findById(dto.idPedido()).orElseGet(() -> {
            Pedido novoPedido = new Pedido();
            return pedidoRepository.save(novoPedido);
        });
        List<ItemPedido> listaDeItens = montarItensDoCarrinho(dto.listaDeItens());
        pedido.setItensPedido(listaDeItens);
        pedido.setCliente(montarDonoDoCarrinho(dto.idCliente()));
        pedidoRepository.save(pedido);
        return dto;
    }

    private List<ItemPedido> montarItensDoCarrinho(List<ItemCarrinhoDTO> listaDto){
        List<ItemPedido> listaDeItens = new ArrayList<>();
        for(ItemCarrinhoDTO item : listaDto){
            ItemPedido novoItemDoCarrinho = new ItemPedido();
            Livro livro = livroRepository.findById(item.idLivro()).orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
            novoItemDoCarrinho.setLivro(livro);
            novoItemDoCarrinho.setQuantidade(item.quantidade());
            listaDeItens.add(novoItemDoCarrinho);
        }
        return listaDeItens;
    }

    private Cliente montarDonoDoCarrinho(Integer idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Necessário fazer cadastro!"));
        return cliente;
    }
}
    

