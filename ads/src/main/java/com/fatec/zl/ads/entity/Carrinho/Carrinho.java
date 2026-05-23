package com.fatec.zl.ads.entity.Carrinho;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    public void visualizarCarrinho() {}

    public void destruirItens() {}

    
}



