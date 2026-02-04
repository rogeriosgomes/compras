package com.inovatte.compras.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity(name = "tb_itemnotaentrada")
public class ItemNotaEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notafiscalentrada_id", nullable = false)
    private NotaFiscalEntrada notaFiscalEntrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="produto_id", nullable = false)
    private Produto produto;
}
