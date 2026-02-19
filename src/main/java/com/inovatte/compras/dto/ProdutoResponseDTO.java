package com.inovatte.compras.dto;

import com.inovatte.compras.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Long id,
        String sku,
        String nome,
        String descricao,
        BigDecimal preco,
        boolean ativo
) {

    public ProdutoResponseDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getSku(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.isAtivo()
        );
    }


}
