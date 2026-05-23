package com.fatec.zl.ads.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.zl.ads.entity.Cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
