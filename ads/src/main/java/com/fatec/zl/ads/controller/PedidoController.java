package com.fatec.zl.ads.controller;


import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.zl.ads.entity.Pedido.DTO.AdicionarAoPedidoDTO;
import com.fatec.zl.ads.entity.Pedido.DTO.PagamentoPedidoDTO;
import com.fatec.zl.ads.entity.Pedido.DTO.PedidoResponseDTO;
import com.fatec.zl.ads.service.PedidoService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    private final SistemaFrete sistemaFrete;
    private final SistemaPagamento sistemaPagamento;

    public PedidoController(PedidoService pedidoService, SistemaFrete sistemaFrete, SistemaPagamento sistemaPagamento) {
        this.pedidoService = pedidoService;
        this.sistemaFrete = sistemaFrete;
        this.sistemaPagamento = sistemaPagamento;
    }

    @GetMapping("/exibir/{idPedido}")
    public ResponseEntity<PedidoResponseDTO> exibirPedido(@PathVariable Integer idPedido) {
        PedidoResponseDTO pedido = pedidoService.exibirPedido(idPedido);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<AdicionarAoPedidoDTO> adicionarNovoPedido(@RequestBody @Valid AdicionarAoPedidoDTO dto) {
        AdicionarAoPedidoDTO dtoResponse = pedidoService.adicionarNovoPedido(dto);
        return ResponseEntity.ok(dtoResponse);
    }  

    @PatchMapping("/confirmar/{idPedido}")
    public ResponseEntity<PedidoResponseDTO> confirmarPedido(@PathVariable Integer idPedido){
        PedidoResponseDTO response = pedidoService.confirmarPedido(idPedido);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/realizar-pagemento")
    public ResponseEntity<Map<String, String>> realizarPagamento(@RequestBody PagamentoPedidoDTO dto){
        BigDecimal valorFrete = sistemaFrete.calcularFrete(dto.endereco());
        PedidoResponseDTO pedido = pedidoService.exibirPedido(dto.idPedido());
        BigDecimal valorPosDesconto = sistemaPagamento.calcularValorFinal(pedido.total(), dto.formaDePagamento(), dto.quantidadeParcelas());
        BigDecimal valorFinal =  valorPosDesconto.add(valorFrete);
        pedidoService.confirmarPagamento(dto.idPedido());
        return ResponseEntity.ok(Map.of("message", "Pagemento de "+ valorFinal +" realizado com sucesso!"));
    } 
}
        

    
    
    

