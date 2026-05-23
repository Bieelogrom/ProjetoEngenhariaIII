import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.Cliente.ClienteDTORequest;
import com.fatec.zl.ads.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class CadastroCliente {
    private final ClienteService clienteService;

    public CadastroCliente (ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCliente(@RequestBody ClienteDTORequest clienteDto) {
        Cliente clienteCadastrado = clienteService.cadastrarCliente(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCadastrado.getNomeCliente()+" cadastrado com sucesso!");
    }

    public void consultaCliente() {

    }

    public void salvarOuAtualizarCliente() {

    }
    
}

