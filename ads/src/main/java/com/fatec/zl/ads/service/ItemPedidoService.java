package com.fatec.zl.ads.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.Cliente.ClienteDTORequest;
import com.fatec.zl.ads.repository.ItemPedidoRepository;


@Service
public class ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService (ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public Cliente cadastrarCliente(ClienteDTORequest cliente){
        Cliente novoCliente = new Cliente();
        novoCliente.setNomeCliente(cliente.NomeCliente());
        return clienteRepository.save(novoCliente);
    }    

    private LocalDate formatarData(String data){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(data, formato);
        return dataConvertida;
    }
}
