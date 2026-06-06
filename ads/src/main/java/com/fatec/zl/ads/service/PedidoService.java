package com.fatec.zl.ads.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.zl.ads.entity.Carrinho.DTO.AdicionarAoPedidoDTO;
import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.ItemPedido.ItemPedido;
import com.fatec.zl.ads.entity.Pedido.Pedido;
import com.fatec.zl.ads.entity.Pedido.StatusPedido;
import com.fatec.zl.ads.repository.CarrinhoRepository;
import com.fatec.zl.ads.repository.ClienteRepository;
import com.fatec.zl.ads.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemPedidoService  itemPedidoService;

    public PedidoService(PedidoRepository pedidoRepository, CarrinhoRepository carrinhoRepository, ClienteRepository clienteRepository, ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.itemPedidoService = itemPedidoService;
    }

    @Transactional
    public AdicionarAoPedidoDTO adicionarNovoPedido(AdicionarAoPedidoDTO dto){
        Pedido pedido = pedidoRepository.findById(dto.idPedido()).orElseGet(Pedido::new);
        List<ItemPedido> listaDeItens = itemPedidoService.montarItensDoCarrinho(dto.listaDeItens());
        pedido.setItensPedido(listaDeItens);
        pedido.setValorTotal(itemPedidoService.montarTotalDoCarrinho(dto.listaDeItens()));
        pedido.setCliente(montarDonoDoCarrinho(dto.idCliente()));
        pedido.setStatus(StatusPedido.valueOf("EM_PROCESSAMENTO"));
        pedidoRepository.save(pedido);
        return dto;
    }

    private Cliente montarDonoDoCarrinho(Integer idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Necessário fazer cadastro!"));
        return cliente;
    }
}
    

