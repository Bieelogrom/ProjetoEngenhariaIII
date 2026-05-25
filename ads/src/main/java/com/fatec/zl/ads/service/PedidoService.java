package com.fatec.zl.ads.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.zl.ads.controller.EstoqueController;
import com.fatec.zl.ads.controller.SistemaBanco;
import com.fatec.zl.ads.controller.SistemaCartao;
import com.fatec.zl.ads.controller.SistemaPagamento;
import com.fatec.zl.ads.entity.Carrinho.Carrinho;
import com.fatec.zl.ads.entity.ItemCarrinho.ItemCarrinho;
import com.fatec.zl.ads.entity.Pedido.Pedido;
import com.fatec.zl.ads.repository.CarrinhoRepository;
import com.fatec.zl.ads.repository.LivroRepository;
import com.fatec.zl.ads.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final LivroRepository livroRepository;

    public PedidoService(PedidoRepository pedidoRepository, CarrinhoRepository carrinhoRepository, LivroRepository livroRepository) {
        this.pedidoRepository = pedidoRepository;
        this.carrinhoRepository = carrinhoRepository;
        this.livroRepository = livroRepository;
    }

    public Pedido exibirPedido (String idCarrinho) {
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho).orElseThrow(() -> new RuntimeException("Carrinho nao encontrado!"));
        if (carrinho.getItens().isEmpty()) {
            throw new RuntimeException("O carrinho esta vazio");
        }
        double valorPedido = 0;
        for(ItemCarrinho item: carrinho.getItens()) {
            valorPedido += item.getLivro().getPreco() * item.getQuantidade();
        }
        valorPedido += calcularFrete(carrinho.getCliente().getEndereco());
        Pedido pedido = new Pedido();
        pedido.setCliente(carrinho.getCliente());
        pedido.setValorTotal(valorPedido);
        pedido.setStatusPedido("Em processamento");
        pedido.setDataPedido(LocalDate.now());
        return pedido;
    }

    public Pedido efetuarPedido (String idCarrinho, String endereco, String formaPagamento, int qteParcelas) {
        SistemaPagamento sp = new SistemaPagamento();
        EstoqueController ec = new EstoqueController(livroRepository);
        List<String> listaAlertas = new ArrayList<>();
        SistemaCartao sc = new SistemaCartao();
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho).get();
        if (carrinho.getItens().isEmpty()) {
            throw new RuntimeException("O carrinho está vazio!");
        }
        Pedido pedido = new Pedido();
        pedido.setCliente(carrinho.getCliente());
        pedido.setDataPedido(LocalDate.now());
        pedido.setQteParcelas(qteParcelas);
        double valorPedido = 0;
        for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
            valorPedido += (itemCarrinho.getLivro().getPreco() * itemCarrinho.getQuantidade());
            String alerta = ec.atualizarEstoque(itemCarrinho.getLivro(), itemCarrinho.getQuantidade());
            if (alerta != null) {
                listaAlertas.add(alerta);
            }
        }
        double frete = calcularFrete(endereco);
        valorPedido += frete;
        pedido.setStatusPedido("Pagamento pendente");
        if (formaPagamento.equals("Pix")) {
            valorPedido = sp.calcularDescontoPix(valorPedido, 8);
            SistemaBanco sb = new SistemaBanco();
            if (!sb.pagarPix(valorPedido)) {
                    throw new RuntimeException("Falha no pagamento!");
            }
            else {
                pedido.setStatusPedido("Confirmado");
            }
        } else if (formaPagamento.equals("Cartao")) {
            if (pedido.getQteParcelas() == 1) {
                valorPedido = sp.calcularDescontoCartao(valorPedido, 3, 1 );
                if (!sc.pagarAVista(valorPedido)){
                    throw new RuntimeException("Falha no pagamento!");
                } else {
                    pedido.setStatusPedido("Confirmado");
                }
            } else {
                if (!sc.pagarParcelado(valorPedido, pedido.getQteParcelas())) {
                    throw new RuntimeException("Falha no pagamento!");
                } else {
                    pedido.setStatusPedido("Confirmado");
                }
            }
        }
        pedido.setStatusPedido("Finalizado");                     
        if (!listaAlertas.isEmpty()) {
            String todosAlertas = String.join(" | ", listaAlertas);
            pedido.setStatusPedido(pedido.getStatusPedido() + " - " + todosAlertas);
        }
        pedido.setValorTotal(valorPedido);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        carrinho.getItens().clear();
        carrinhoRepository.save(carrinho);
        return pedidoSalvo;
    }

    public double calcularFrete(String endereco) {
        double freteFixo = 18.00;
        return freteFixo;
    }
    
}
    

