package com.fatec.zl.ads.entity.Livro;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("DIGITAL")
public class LivroDigital extends Livro {
    public LivroDigital(){
        this.setPercentualDesconto(new BigDecimal("0.10"));
    }
}
