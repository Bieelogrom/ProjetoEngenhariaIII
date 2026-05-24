package com.fatec.zl.ads.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import com.fatec.zl.ads.controller.SistemaBanco;
import com.fatec.zl.ads.controller.SistemaCartao;
import com.fatec.zl.ads.controller.SistemaPagamento;
import com.fatec.zl.ads.entity.Carrinho.Carrinho;
import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.ItemCarrinho.ItemCarrinho;
import com.fatec.zl.ads.entity.ItemPedido.ItemPedido;
import com.fatec.zl.ads.entity.Pedido.Pedido;
import com.fatec.zl.ads.repository.CarrinhoRepository;
import com.fatec.zl.ads.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final CarrinhoRepository carrinhoRepository;

    public PedidoService(PedidoRepository pedidoRepository, CarrinhoRepository carrinhoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.carrinhoRepository = carrinhoRepository;
    }

    private LocalDate formatarData(String data){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data, formato);
        return dataConvertida;
    }

    public Pedido efetuarPedido (String idCarrinho, String endereco, String formaPagamento) {
        SistemaPagamento sp = new SistemaPagamento();
        double valorPedido = 0;
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho).get();
        if (carrinho.getItens().isEmpty()) {
            throw new RuntimeException("O carrinho está vazio!");
        }
        Cliente cliente = carrinho.getCliente();
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDate.now());

        for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
            ItemPedido ip = new ItemPedido();
            ip.setLivro(itemCarrinho.getLivro());                               //VIZUALIZAÇÃO DOS ITENS
            ip.setQuantidade(itemCarrinho.getQuantidade());
            pedido.getItensPedido().add(ip);
            double precoLivro  = itemCarrinho.getLivro().getPreco();
            valorPedido += (precoLivro * itemCarrinho.getQuantidade());

            String alerta = estoqueService.atualizarEstoque(itemCarrinho.getLivro(), itemCarrinho.getQuantidade());
            if (alerta != null) {
            }

        }

        double frete = calcularFrete(endereco);
        valorPedido += frete;
        pedido.setStatusPedido("Pagamento pendente");
        int porcentagem = 0;

        if (formaPagamento.equals("Pix")) {
            sp.calcularDescontoPix(valorPedido, 8);
            SistemaBanco sb = new SistemaBanco();
            if (!sb.pagarPix(valorPedido)) {
                    throw new RuntimeException("Falha no pagamento!");
            }
            else {
                pedido.setStatusPedido("Confirmado");
            }
        } else if (formaPagamento.equals("Cartao")) {
            SistemaCartao sc = new SistemaCartao();
            if (pedido.qteParcelas == 1) {
                sc.calcularDescontoAVista(valor, 3);
                if (!sc.pagarAVista(valorPedido)){
                    throw new RuntimeException("Falha no pagamento!");
                } else {
                    pedido.setStatusPedido("Confirmado");
                }
            }
            } else {
                if (!sc.pagarParcelado(valorPedido, pedido.qteParcelas)) {
                    throw new RuntimeException("Falha no pagamento!");
                } else {
                    pedido.setStatusPedido("Confirmado");
                }
            }
            String alerta = estoqueService.atualizarEstoque(itemCarrinho.getLivro(), itemCarrinho.getQuantidade());
            if (alerta != null) {
            }
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
    

