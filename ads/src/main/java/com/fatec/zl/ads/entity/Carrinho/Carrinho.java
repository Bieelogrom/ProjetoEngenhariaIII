package com.fatec.zl.ads.entity.Carrinho;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatec.zl.ads.entity.Cliente.Cliente;
import com.fatec.zl.ads.entity.ItemCarrinho.ItemCarrinho;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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

@Entity(name = "carrinhos")
@Table(name = "carrinhos")
@NoArgsConstructor
@Getter
@Setter
public class Carrinho {

    @Id
    private String id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carrinho")
    private List<ItemCarrinho> itens = new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

}

    




