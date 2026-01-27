package com.inovatte.compras.service;

import com.inovatte.compras.dto.FornecedorRequestDTO;
import com.inovatte.compras.dto.FornecedorResponseDTO;
import com.inovatte.compras.model.Fornecedor;
import com.inovatte.compras.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public List<FornecedorResponseDTO> listarTodos(){

        List<FornecedorResponseDTO> fornecedores = repository.findAll()
                .stream()
                .map(FornecedorResponseDTO::new)
                .toList();

        return fornecedores;

    }

    public FornecedorResponseDTO listarId(Long id){

        Fornecedor fornecedor = repository.findById(id).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        return new FornecedorResponseDTO(fornecedor);

    }

    public FornecedorResponseDTO cadastrar(FornecedorRequestDTO fornecedorRequestDTO){

        var fornecedorNovo = new Fornecedor(fornecedorRequestDTO);

        if(repository.existsByCnpj(fornecedorNovo.getCnpj())){

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Já existe um fornecedor com este cnpj."
            );
        }

        var fornecedor = repository.save(fornecedorNovo);

        return new FornecedorResponseDTO(fornecedor);

    }

    public FornecedorResponseDTO alterar(Long id, FornecedorRequestDTO fornecedorRequestDTO){
        Fornecedor fornecedor = repository.findById(id).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        fornecedor.setRazaoSocial(fornecedorRequestDTO.razaoSocial());
        fornecedor.setCnpj(fornecedorRequestDTO.cnpj());

        var fornecedorAlterado = repository.save(fornecedor);

        return new FornecedorResponseDTO(fornecedorAlterado);

    }

    public void excluir(Long id){
        var fornecedor = repository.findById(id).orElseThrow(()-> new RuntimeException("Fornecedor não encontrado"));

        repository.delete(fornecedor);
    }
}
