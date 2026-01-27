package com.inovatte.compras.repository;

import com.inovatte.compras.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByNome(String nome);

    boolean existsBySku(String sku);
}
