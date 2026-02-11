package com.inovatte.compras.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "tb_estoque")
@Data
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidadeAtual;
    private Integer quantidadeReservada;

    @OneToOne(mappedBy = "estoque")
    private Produto produto;

}
