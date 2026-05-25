package com.fatec.zl.ads.entity.Livro.Exception;

public class FormatoNaoReconhecidoException extends RuntimeException {
    public FormatoNaoReconhecidoException(){
        super("Formato de livro não reconhecido!");
    }
}
