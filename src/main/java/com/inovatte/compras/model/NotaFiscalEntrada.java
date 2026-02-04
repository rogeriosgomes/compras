package com.inovatte.compras.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.inovatte.compras.dto.NotaFiscalEntradaRequestoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "tb_notafiscalentrada")
@NoArgsConstructor

public class NotaFiscalEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numero;
    private String serie;
    private LocalDate dataEmissao;
    private LocalDate dataEntrada;
    private BigDecimal valorTotal;
    private StatusNota statusNota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fonecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @JsonIgnore
    @OneToMany(mappedBy = "notaFiscalEntrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemNotaEntrada> items = new ArrayList<>();


}
