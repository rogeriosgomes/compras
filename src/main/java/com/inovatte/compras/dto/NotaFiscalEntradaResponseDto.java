package com.inovatte.compras.dto;

import com.inovatte.compras.model.Fornecedor;
import com.inovatte.compras.model.NotaFiscalEntrada;
import com.inovatte.compras.model.StatusNota;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaFiscalEntradaResponseDto(
        Long id,
        String numero,
        String serie,
        LocalDate dataEmissao,
        LocalDate dataEntrada,
        Fornecedor fornecedor,
        BigDecimal valorTotal,
        StatusNota statusNota) {

    public NotaFiscalEntradaResponseDto(NotaFiscalEntrada notaFiscalEntrada){
        this(
                notaFiscalEntrada.getId(),
                notaFiscalEntrada.getNumero(),
                notaFiscalEntrada.getSerie(),
                notaFiscalEntrada.getDataEmissao(),
                notaFiscalEntrada.getDataEntrada(),
                notaFiscalEntrada.getFornecedor(),
                notaFiscalEntrada.getValorTotal(),
                notaFiscalEntrada.getStatusNota()
        );
    }
}
