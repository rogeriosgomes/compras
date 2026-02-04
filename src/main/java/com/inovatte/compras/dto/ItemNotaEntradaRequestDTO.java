package com.inovatte.compras.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemNotaEntradaRequestDTO(
        Long idNotaEntrada,
        Long idProduto,
        @NotNull(message = "Quantidade deve ser maior que zero")
        Integer    quantidade,
        @NotNull(message = "O preço unitário dever ser maiar que zero")
        BigDecimal precoUnitario
        ) {
}
