package com.fatec.zl.ads.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.Cliente.ClienteDTORequest;
import com.fatec.zl.ads.repository.ClienteRepository;


@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService (ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrarCliente(ClienteDTORequest cliente){
        Cliente novoCliente = new Cliente();
        novoCliente.setNomeCliente(cliente.NomeCliente());
        novoCliente.setCPF(cliente.CPF());
        novoCliente.setDataNasc(formatarData(cliente.dataNasc()));
        novoCliente.setEmail(cliente.email());
        novoCliente.setTelefone(cliente.telefone());
        novoCliente.setEndereco(cliente.endereco());
        novoCliente.setStatusCadastro(cliente.statusCadastro());
        return clienteRepository.save(novoCliente);
    }   
    
    public List<Cliente> listarClientes (){
        List<Cliente> listaClientes = clienteRepository.findAll();
        return listaClientes;
    }

    private LocalDate formatarData(String data){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data, formato);
        return dataConvertida;
    }
}