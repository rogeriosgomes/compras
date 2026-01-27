package com.inovatte.compras.controller;

import com.inovatte.compras.dto.FornecedorRequestDTO;
import com.inovatte.compras.dto.FornecedorResponseDTO;
import com.inovatte.compras.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class ForncedorController {

    @Autowired
    private FornecedorService service;

    @GetMapping
    public ResponseEntity<List<FornecedorResponseDTO>> listarTodos(){

        var fornecedores = service.listarTodos();

        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> listarId(@PathVariable Long id){

        var fornecedor = service.listarId(id);

        return ResponseEntity.ok(fornecedor);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<FornecedorResponseDTO> cadastrar(@Valid @RequestBody FornecedorRequestDTO fornecedorRequestDTO){

        var fornecedor = service.cadastrar(fornecedorRequestDTO);

        return ResponseEntity.ok(fornecedor);
    }

    @PutMapping("{id}")
    public ResponseEntity<FornecedorResponseDTO> alterar(@PathVariable Long id, @RequestBody FornecedorRequestDTO fornecedorRequestDTO){

        var fornecedor = service.alterar(id, fornecedorRequestDTO);

        return ResponseEntity.ok(fornecedor);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
