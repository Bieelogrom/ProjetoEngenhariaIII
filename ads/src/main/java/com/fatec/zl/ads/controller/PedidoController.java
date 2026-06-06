package com.fatec.zl.ads.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.zl.ads.entity.Pedido.Pedido;
import com.fatec.zl.ads.entity.Pedido.PedidoDTORequest;
import com.fatec.zl.ads.service.PedidoService;



@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // @GetMapping("/exibir/{idCarrinho}")
    // public ResponseEntity<Pedido> exibirPedido(@PathVariable String idCarrinho) {
    //     Pedido pedido = pedidoService.exibirPedido(idCarrinho);
    //     return ResponseEntity.ok(pedido);
    // }

    // @PostMapping("/efetuar")
    // public ResponseEntity<?> efetuarPedido(@RequestBody PedidoDTORequest pedidoDto) {
    //     try {
    //         Pedido pedido = pedidoService.efetuarPedido(pedidoDto.idCarrinho(), pedidoDto.enderecoEntrega(), pedidoDto.formaPagamento(), pedidoDto.qteParcelas());
    //         return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.badRequest().body("{\"erro\": \"" + e.getMessage() + "\"}");
    //     }
    // }
}
        

    
    
    

