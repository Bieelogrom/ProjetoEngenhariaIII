package com.fatec.zl.ads.entity.Cliente;

import java.time.LocalDate;

import com.fatec.zl.ads.entity.Carrinho.Carrinho;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "clientes")
@Table(name = "clientes")
@NoArgsConstructor
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 120)
    private String NomeCliente;
    @Column(nullable = false)
    private String CPF;
    @Column(nullable = false)
    private LocalDate dataNasc;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private String statusCadastro;
    private Carrinho carrinho;

    public Cliente (String NomeCliente, String CPF, LocalDate dataNasc, String email, String telefone, String statusCadastro) {
        this.NomeCliente = NomeCliente;
        this.CPF = CPF;
        this.dataNasc = dataNasc;
        this.email = email;
        this.telefone = telefone;
        this.statusCadastro = statusCadastro;
    }

}
