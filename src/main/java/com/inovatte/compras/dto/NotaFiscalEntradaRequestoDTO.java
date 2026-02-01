package com.inovatte.compras.dto;

import com.inovatte.compras.model.Fornecedor;
import com.inovatte.compras.model.StatusNota;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaFiscalEntradaRequestoDTO(
        String numero,
        String serie,
        LocalDate dataEmissao,
        LocalDate dataEntrada,
        Long fornecedorId) {
}
