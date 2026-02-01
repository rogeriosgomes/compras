package com.inovatte.compras.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.inovatte.compras.dto.FornecedorRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "tb_fornecedor")
@NoArgsConstructor

public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String razaoSocial;
    private String cnpj;
    private boolean ativo;
    @JsonIgnore
    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotaFiscalEntrada> notas = new ArrayList<>();


    public Fornecedor(FornecedorRequestDTO dto){
        this.razaoSocial = dto.razaoSocial();
        this.cnpj = dto.cnpj();
        this.ativo = true;

    }


}
