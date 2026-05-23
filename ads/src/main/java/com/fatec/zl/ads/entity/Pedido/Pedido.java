package com.fatec.zl.ads.entity.Pedido;

import java.time.LocalDate;
import java.util.List;

import com.fatec.zl.ads.entity.Livro.Livro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "pedidos")
@Table(name = "pedidos")
@NoArgsConstructor
@Getter
@Setter
public class Pedido {

    @Column(nullable = false)
    private String statusPedido;
    @Column(nullable = false)
    private List<Livro> itensPedido;

    public Pedido(String statusPedido, List<Livro> itensPedido) {
        this.statusPedido = statusPedido;
        this.itensPedido = itensPedido;

    }


    
}
