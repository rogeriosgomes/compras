package com.inovatte.compras.dto;

import com.inovatte.compras.model.ItemNotaEntrada;
import com.inovatte.compras.model.NotaFiscalEntrada;
import com.inovatte.compras.model.Produto;

import java.math.BigDecimal;

public record ItemNotaEntradaResponseDto(
        NotaFiscalEntrada notaFiscalEntrada,
        Produto           produto,
        Long              id,
        Integer           quantidade,
        BigDecimal        precoUnitario,
        BigDecimal        subtotal,
        Long              idNotaEntrada) {

    public ItemNotaEntradaResponseDto(ItemNotaEntrada itemNotaEntrada){
        this(
                itemNotaEntrada.getNotaFiscalEntrada(),
                itemNotaEntrada.getProduto(),
                itemNotaEntrada.getId(),
                itemNotaEntrada.getQuantidade(),
                itemNotaEntrada.getPrecoUnitario(),
                itemNotaEntrada.getSubtotal(),
                itemNotaEntrada.getId()
        );
    }
}
