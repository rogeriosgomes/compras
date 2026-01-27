package com.inovatte.compras.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record ProdutoRequestDTO(
                                @NotBlank(message = "O Nome é Obrigatório")
                                String nome,
                                String descricao,
                                @NotNull(message = "O Preço é obrigatório")
                                BigDecimal preco,
                                @NotBlank(message = "O Sku é obrigatório")
                                String sku) {


}
