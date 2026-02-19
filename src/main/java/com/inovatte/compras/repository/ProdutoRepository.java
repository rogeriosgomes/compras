package com.inovatte.compras.repository;

import com.inovatte.compras.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    boolean existsByNome(String nome);

    boolean existsBySku(String sku);
}
