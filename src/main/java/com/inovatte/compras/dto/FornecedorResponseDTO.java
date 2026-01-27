package com.inovatte.compras.dto;

import com.inovatte.compras.model.Fornecedor;

public record FornecedorResponseDTO(
        Long id,
        String razaoSocial,
        String cnpj,
        boolean ativo
) {

    public FornecedorResponseDTO(Fornecedor fornecedor){
         this(
                 fornecedor.getId(),
                 fornecedor.getRazaoSocial(),
                 fornecedor.getCnpj(),
                 fornecedor.isAtivo()
         );

    }
}
