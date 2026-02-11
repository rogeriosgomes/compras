package com.inovatte.compras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inovatte.compras.dto.ProdutoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_produto")
@Data
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int quantidadeEstoque;
    private String sku;
    private boolean ativo;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemNotaEntrada> itemNotaEntradaList = new ArrayList<>();
    @OneToOne
    @JoinColumn(name= "estoque_id")
    private Estoque estoque;



    public Produto(){}

    public Produto(ProdutoRequestDTO dto){
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.sku   = dto.sku();
        this.ativo = true;
        this.quantidadeEstoque = 0;
        this.preco = dto.preco();
        this.dataCadastro = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();



    }
}
