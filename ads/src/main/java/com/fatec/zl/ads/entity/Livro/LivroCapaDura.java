package com.fatec.zl.ads.entity.Livro;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CAPA_DURA")
@Getter
@Setter
@NoArgsConstructor
public class LivroCapaDura extends LivroFisico {
    
}
