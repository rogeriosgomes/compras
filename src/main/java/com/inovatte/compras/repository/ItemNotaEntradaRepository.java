package com.inovatte.compras.repository;

import com.inovatte.compras.model.ItemNotaEntrada;
import com.inovatte.compras.model.NotaFiscalEntrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemNotaEntradaRepository extends JpaRepository<ItemNotaEntrada, Long> {



    Optional<ItemNotaEntrada> findByNotaFiscalEntrada(NotaFiscalEntrada nota);
}
