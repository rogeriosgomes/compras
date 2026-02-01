package com.inovatte.compras.model;

public enum StatusNota {

    RASCUNHO(1, "Rascunho"),
    CONFIRMADA(2, "Confirmado"),
    CANCELADA(3, "Cancelada");

    private final int codigo;
    private final String descricao;

    StatusNota(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo(){
        return codigo;
    }

    public String getDescricao(){
        return descricao;
    }

    public static  StatusNota fromCodigo(int codigo){
        for(StatusNota status : values()){
            if(status.codigo == codigo){
                return status;
            }
        }

        throw  new IllegalArgumentException("Código Inválido: "+ codigo);
    }

}
