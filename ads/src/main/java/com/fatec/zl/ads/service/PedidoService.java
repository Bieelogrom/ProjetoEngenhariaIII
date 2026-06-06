package com.fatec.zl.ads.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.ItemPedido.ItemPedido;
import com.fatec.zl.ads.entity.Pedido.Pedido;
import com.fatec.zl.ads.entity.Pedido.StatusPedido;
import com.fatec.zl.ads.entity.Pedido.DTO.AdicionarAoPedidoDTO;
import com.fatec.zl.ads.entity.Pedido.DTO.PedidoResponseDTO;
import com.fatec.zl.ads.entity.Pedido.Mapper.PedidoResponseMapper;
import com.fatec.zl.ads.repository.CarrinhoRepository;
import com.fatec.zl.ads.repository.ClienteRepository;
import com.fatec.zl.ads.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemPedidoService  itemPedidoService;
    private final PedidoResponseMapper pedidoResponseMapper;

    public PedidoService(PedidoRepository pedidoRepository, CarrinhoRepository carrinhoRepository, ClienteRepository clienteRepository, ItemPedidoService itemPedidoService, PedidoResponseMapper pedidoResponseMapper) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.itemPedidoService = itemPedidoService;
        this.pedidoResponseMapper = pedidoResponseMapper;
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

    public PedidoResponseDTO confirmarPedido(Integer idPedido){
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
        pedido.setStatus(StatusPedido.valueOf("PAGAMENTO_PENDENTE"));
        return pedidoResponseMapper.toDTO(pedidoRepository.save(pedido));
    }

    private Cliente montarDonoDoCarrinho(Integer idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Necessário fazer cadastro!"));
        return cliente;
    }
}
    

