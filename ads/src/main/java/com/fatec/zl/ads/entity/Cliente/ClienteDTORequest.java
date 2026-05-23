package com.fatec.zl.ads.entity.Cliente;

public record ClienteDTORequest(String NomeCliente, String CPF, String dataNasc, String email, 
                                    String telefone, String statusCadastro) {

}