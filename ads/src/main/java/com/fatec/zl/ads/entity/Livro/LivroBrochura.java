package com.fatec.zl.ads.entity.Livro;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("BROCHURA")
@Getter
@Setter
public class LivroBrochura extends LivroFisico {
    public LivroBrochura(){
        this.setPercentualDesconto(new BigDecimal("0.05"));
    }
}
