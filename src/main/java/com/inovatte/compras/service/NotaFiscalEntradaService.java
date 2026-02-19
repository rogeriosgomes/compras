package com.inovatte.compras.service;

import com.inovatte.compras.dto.NotaFiscalEntradaRequestoDTO;
import com.inovatte.compras.dto.NotaFiscalEntradaResponseDTO;
import com.inovatte.compras.model.Estoque;
import com.inovatte.compras.model.ItemNotaEntrada;
import com.inovatte.compras.model.NotaFiscalEntrada;
import com.inovatte.compras.model.StatusNota;
import com.inovatte.compras.repository.EstoqueRepository;
import com.inovatte.compras.repository.FornecedorRepository;
import com.inovatte.compras.repository.ItemNotaEntradaRepository;
import com.inovatte.compras.repository.NotaFiscalEntradaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaFiscalEntradaService {

    @Autowired
    private NotaFiscalEntradaRepository notaFiscalEntradaRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;
    @Autowired
    private ItemNotaEntradaRepository itemNotaEntradaRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;

    public NotaFiscalEntradaResponseDTO entrada(NotaFiscalEntradaRequestoDTO dto){

        NotaFiscalEntrada nota = new NotaFiscalEntrada();

        if(notaFiscalEntradaRepository.existsByNumero(dto.numero())){
            throw new RuntimeException("Já existe uma nota com esse número cadastrada.");
        }
        nota.setNumero(dto.numero());
        nota.setSerie(dto.serie());
        nota.setDataEntrada(dto.dataEntrada());
        nota.setDataEmissao(dto.dataEmissao());
        var fornecedor = fornecedorRepository.findById(dto.fornecedorId()).orElseThrow(()-> new RuntimeException("Fornecedor não encontrado"));
        nota.setFornecedor(fornecedor);
        nota.setStatusNota(StatusNota.RASCUNHO);
        nota.setValorTotal(BigDecimal.valueOf(0));

        var notaSalva = notaFiscalEntradaRepository.save(nota);

        return new NotaFiscalEntradaResponseDTO(notaSalva);

    }

    public NotaFiscalEntradaResponseDTO listarPorid(Long id){

        var nota = notaFiscalEntradaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nota de Entrada não encontrada."));

        return new NotaFiscalEntradaResponseDTO(nota);
    }

    public List<NotaFiscalEntradaResponseDTO> listarTodos(){

        List<NotaFiscalEntradaResponseDTO> notas = notaFiscalEntradaRepository.findAll()
                                                    .stream()
                                                    .map(NotaFiscalEntradaResponseDTO::new)
                                                    .toList();



        return notas;
    }

    public ResponseEntity<String> alterarStatus(Long id, StatusNota statusNota, String retorno){

        var nota = notaFiscalEntradaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nota de Entrada não encontrada."));

        nota.setStatusNota(statusNota);

        notaFiscalEntradaRepository.save(nota);

        return ResponseEntity.ok(retorno);

    }

    @Transactional
    public void CalcularValorTotal(Long id){

        var nota = notaFiscalEntradaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nota de Entrada não encontrada"));

        var valorTotal = itemNotaEntradaRepository.findByNotaFiscalEntrada(nota).stream()
                .map(i -> i.getPrecoUnitario().multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        nota.setValorTotal(valorTotal);

    }

    @Transactional
    public void AjustarEstoque(Long id){

        var nota = notaFiscalEntradaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nota de Entrada não encontrada"));

        itemNotaEntradaRepository.findByNotaFiscalEntrada(nota)
                .stream()
                .collect(Collectors.groupingBy(
                        ItemNotaEntrada::getProduto,
                        Collectors.summingInt(ItemNotaEntrada::getQuantidade)
                ))
                .forEach(((produto, quantidadeTotal) ->{
                    Estoque estoque = estoqueRepository.findByProduto(produto);

                    if(estoque == null){
                        estoque = new Estoque();
                        estoque.setProduto(produto);
                        estoque.setQuantidadeAtual(0);
                    }

                    estoque.setQuantidadeAtual(
                            estoque.getQuantidadeAtual() + quantidadeTotal
                    );

                    estoqueRepository.save(estoque);
                } ));





    }

    //desenvolver alteração da nota
    //desenvolver exclusão da nota
    //desenvolver calcular valor Total da nota - ajustar o estoque feito


}
