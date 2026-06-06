package com.fatec.zl.ads.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.zl.ads.entity.Pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, String> {

}
