package com.fatec.zl.ads.entity.Livro;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class LivroFisico extends Livro {
    @Column(nullable = true)
    private Integer quantidade;

    public boolean isEstoqueBaixo(){
        return this.quantidade != null && this.quantidade <= 2;
    }
}   
