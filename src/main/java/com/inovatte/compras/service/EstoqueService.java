package com.inovatte.compras.service;

import com.inovatte.compras.model.Estoque;
import com.inovatte.compras.model.Produto;
import com.inovatte.compras.repository.EstoqueRepository;
import com.inovatte.compras.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public void darEntrada(Long idProduto, Integer quantidade){

        var produto = produtoRepository.findById(idProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        if (produto.isAtivo()){
            Estoque estoque = estoqueRepository.findByProduto(produto);
            estoque.setQuantidadeAtual(quantidade);
            estoqueRepository.save(estoque);
        }else{
            throw new RuntimeException("O produto informado não está ativo");
        }

    }
}
