package com.inovatte.compras.service;

import com.inovatte.compras.dto.NotaFiscalEntradaRequestoDTO;
import com.inovatte.compras.dto.NotaFiscalEntradaResponseDto;
import com.inovatte.compras.model.NotaFiscalEntrada;
import com.inovatte.compras.model.StatusNota;
import com.inovatte.compras.repository.FornecedorRepository;
import com.inovatte.compras.repository.NotaFiscalEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class NotaFiscalEntradaService {

    @Autowired
    private NotaFiscalEntradaRepository notaFiscalEntradaRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public NotaFiscalEntradaResponseDto entrada(NotaFiscalEntradaRequestoDTO dto){

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

        return new NotaFiscalEntradaResponseDto(notaSalva);

    }

    public NotaFiscalEntradaResponseDto listarPorid(Long id){

        var nota = notaFiscalEntradaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nota de Entrada não encontrada."));

        return new NotaFiscalEntradaResponseDto(nota);
    }

    public List<NotaFiscalEntradaResponseDto> listarTodos(){

        List<NotaFiscalEntradaResponseDto> notas = notaFiscalEntradaRepository.findAll()
                                                    .stream()
                                                    .map(NotaFiscalEntradaResponseDto::new)
                                                    .toList();



        return notas;
    }

    public ResponseEntity<String> alterarStatus(Long id, StatusNota statusNota, String retorno){

        var nota = notaFiscalEntradaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nota de Entrada não encontrada."));

        nota.setStatusNota(statusNota);

        notaFiscalEntradaRepository.save(nota);

        return ResponseEntity.ok(retorno);

    }

    //desenvolver alteração da nota
    //desenvolver exclusão da nota
    //desenvolver calcular valor Total da nota


}
