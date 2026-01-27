package com.inovatte.compras.model;

import com.inovatte.compras.dto.FornecedorRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String razaoSocial;
    private String cnpj;
    private boolean ativo;

    public Fornecedor(){}

    public Fornecedor(FornecedorRequestDTO dto){
        this.razaoSocial = dto.razaoSocial();
        this.cnpj = dto.cnpj();
        this.ativo = true;

    }


}
