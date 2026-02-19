package com.inovatte.compras.service;

import com.inovatte.compras.dto.ProdutoRequestDTO;
import com.inovatte.compras.dto.ProdutoResponseDTO;
import com.inovatte.compras.model.Produto;
import com.inovatte.compras.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoResponseDTO> listaTodos(){

        List<ProdutoResponseDTO> produtos = produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDTO::new)
                .toList();

        return produtos;
    }

    public ProdutoResponseDTO listaId(Long id){

        var produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        return new ProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO cadastrar(ProdutoRequestDTO produtoDto){

        Produto produtoNovo = new Produto(produtoDto);
        if(produtoRepository.existsByNome(produtoNovo.getNome())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Já existe um produto com esse nome cadastrado"
            );

        }

        if(produtoRepository.existsBySku(produtoNovo.getSku())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Já existe um produto com esse sku cadastrado"
            );

        }
        produtoRepository.save(produtoNovo);

        return new ProdutoResponseDTO(produtoNovo);
    }

    public ProdutoResponseDTO alterar(Long id, ProdutoRequestDTO produtoRequestDTO){
        var produto = produtoRepository.findById(id).orElseThrow(()-> new RuntimeException("Produto não encontrado."));

        produto.setNome(produtoRequestDTO.nome());
        produto.setDescricao(produtoRequestDTO.descricao());
        produto.setPreco(produtoRequestDTO.preco());
        produto.setSku(produtoRequestDTO.sku());

        var produtoAlterado = produtoRepository.save(produto);

        return new ProdutoResponseDTO(produtoAlterado);
    }

    public void excluir(Long id){

        var produto = produtoRepository.findById(id).orElseThrow(()-> new RuntimeException("Produto não Encontrado."));

        produtoRepository.delete(produto);


    }
}
