package com.fatec.zl.ads.entity.Pedido;

public enum StatusPedido {
    EM_PROCESSAMENTO("Em processamento"),
    PAGAMENTO_PENDENTE("Pagamento pendente"),
    CONFIRMADO("Confirmado"),
    EM_TRANSPORTE("Em transporte"),
    FINALIZADO("Finalizado");

    private final String descricao;

    StatusPedido(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
