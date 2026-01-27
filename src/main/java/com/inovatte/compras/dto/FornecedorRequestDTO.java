package com.inovatte.compras.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FornecedorRequestDTO (
         @NotNull(message = "O campo razão social é obrigatório.")
         String razaoSocial,
         @NotNull(message = "O campo cnpj social é obrigatório.")
         String cnpj){

}