package com.inovatte.compras.repository;

import com.inovatte.compras.model.NotaFiscalEntrada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaFiscalEntradaRepository extends JpaRepository<NotaFiscalEntrada, Long> {

    boolean existsByNumero(String numero);
}
