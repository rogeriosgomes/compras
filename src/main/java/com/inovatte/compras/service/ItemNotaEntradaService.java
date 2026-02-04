package com.inovatte.compras.service;

import com.inovatte.compras.dto.ItemNotaEntradaRequestDTO;
import com.inovatte.compras.dto.ItemNotaEntradaResponseDto;
import com.inovatte.compras.model.ItemNotaEntrada;
import com.inovatte.compras.model.NotaFiscalEntrada;
import com.inovatte.compras.repository.ItemNotaEntradaRepository;
import com.inovatte.compras.repository.NotaFiscalEntradaRepository;
import com.inovatte.compras.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemNotaEntradaService {

    @Autowired
    private ItemNotaEntradaRepository itemNotaEntradaRepository;
    @Autowired
    private NotaFiscalEntradaRepository notaFiscalEntradaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public ItemNotaEntradaResponseDto adicionar(ItemNotaEntradaRequestDTO dto){

        var idNotaFiscalEntrada = notaFiscalEntradaRepository.findById(dto.idNotaEntrada()).orElseThrow(() -> new RuntimeException("Nota Fiscal de Entrada não encontrada"));
        var idProduto = produtoRepository.findById(dto.idProduto()).orElseThrow(() -> new RuntimeException("Produto não encontado"));

        var itemNotaEntrada = new ItemNotaEntrada();
        itemNotaEntrada.setNotaFiscalEntrada(idNotaFiscalEntrada);
        itemNotaEntrada.setProduto(idProduto);
        itemNotaEntrada.setPrecoUnitario(dto.precoUnitario());
        itemNotaEntrada.setQuantidade(dto.quantidade());
        var subTotal = dto.precoUnitario().multiply(BigDecimal.valueOf(dto.quantidade()));
        itemNotaEntrada.setSubtotal(subTotal);

        itemNotaEntradaRepository.save(itemNotaEntrada);

        return new ItemNotaEntradaResponseDto(itemNotaEntrada);
    }
}
