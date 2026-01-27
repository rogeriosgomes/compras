package com.inovatte.compras.controller;

import com.inovatte.compras.dto.ProdutoRequestDTO;
import com.inovatte.compras.dto.ProdutoResponseDTO;
import com.inovatte.compras.repository.ProdutoRepository;
import com.inovatte.compras.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/teste")
    public String teste(){
        return ("Teste de acesso ao produto!!!");
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos(){

        var produtos = produtoService.listaTodos();

        return  ResponseEntity.ok(produtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> listaId(@PathVariable Long id){

        var produto = produtoService.listaId(id);

        return ResponseEntity.ok(produto);
    }


    @PostMapping("/novo")
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@Valid  @RequestBody ProdutoRequestDTO produtoDto){

        var produtoNovo = produtoService.cadastrar(produtoDto);

        return ResponseEntity.ok(produtoNovo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> alterar(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO){

        var produto = produtoService.alterar(id, produtoRequestDTO);

        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> excluir(@PathVariable Long id){

        produtoService.excluir(id);

        return ResponseEntity.noContent().build();

    }


}
