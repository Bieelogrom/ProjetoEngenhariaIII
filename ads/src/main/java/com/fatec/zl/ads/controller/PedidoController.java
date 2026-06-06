package com.fatec.zl.ads.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.zl.ads.entity.Pedido.DTO.AdicionarAoPedidoDTO;
import com.fatec.zl.ads.entity.Pedido.DTO.PedidoResponseDTO;
import com.fatec.zl.ads.service.PedidoService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/exibir/{idCarrinho}")
    public ResponseEntity<PedidoResponseDTO> exibirPedido(@PathVariable Integer idCarrinho) {
        PedidoResponseDTO pedido = pedidoService.exibirPedido(idCarrinho);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<AdicionarAoPedidoDTO> adicionarNovoPedido(@RequestBody @Valid AdicionarAoPedidoDTO dto) {
        AdicionarAoPedidoDTO dtoResponse = pedidoService.adicionarNovoPedido(dto);
        return ResponseEntity.ok(dtoResponse);
    }  

    @PatchMapping("/confirmar")
    public ResponseEntity<PedidoResponseDTO> confirmarPedido(@PathVariable Integer pedidoId){
        PedidoResponseDTO response = pedidoService.confirmarPedido(pedidoId);
        return ResponseEntity.ok(response);
    }
}
        

    
    
    

