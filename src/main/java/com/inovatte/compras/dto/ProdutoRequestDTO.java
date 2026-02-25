package com.inovatte.compras.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record ProdutoRequestDTO(
                                @NotBlank(message = "O campo Nome é Obrigatório")
                                String nome,
                                String descricao,
                                @NotNull(message = "O campo Preço é obrigatório")
                                BigDecimal preco,
                                @NotBlank(message = "O campo SKU é obrigatório")
                                String sku) {


}
