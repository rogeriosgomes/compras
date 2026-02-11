package com.inovatte.compras.repository;

import com.inovatte.compras.model.Estoque;
import com.inovatte.compras.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Estoque findByProduto(Produto produto);
}
