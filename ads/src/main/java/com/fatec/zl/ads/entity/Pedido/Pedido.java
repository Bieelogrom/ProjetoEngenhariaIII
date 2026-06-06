package com.fatec.zl.ads.entity.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.ItemPedido.ItemPedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataPedido;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido")
    private List<ItemPedido> itensPedido = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @Column(nullable = true)
    private int qteParcelas;
}
