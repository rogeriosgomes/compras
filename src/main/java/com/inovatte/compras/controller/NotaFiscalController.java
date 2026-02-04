package com.inovatte.compras.controller;

import com.inovatte.compras.dto.NotaFiscalEntradaRequestoDTO;
import com.inovatte.compras.dto.NotaFiscalEntradaResponseDTO;
import com.inovatte.compras.model.StatusNota;
import com.inovatte.compras.service.NotaFiscalEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/nota")
@RestController
public class NotaFiscalController {


    @Autowired
    private NotaFiscalEntradaService notaFiscalEntradaService;

    @PostMapping
    public ResponseEntity<NotaFiscalEntradaResponseDTO> entrada(@RequestBody NotaFiscalEntradaRequestoDTO notaFiscalEntradaRequestoDTO){
        var nota = notaFiscalEntradaService.entrada(notaFiscalEntradaRequestoDTO);
        return ResponseEntity.ok(nota);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscalEntradaResponseDTO> listarPorId(@PathVariable Long id){
        var nota = notaFiscalEntradaService.listarPorid(id);

        return ResponseEntity.ok(nota);
    }

    @GetMapping
    public ResponseEntity<List<NotaFiscalEntradaResponseDTO>> litarTodas(){

        var notas = notaFiscalEntradaService.listarTodos();

        return ResponseEntity.ok(notas);
    }

    @PutMapping("/confirmar/{id}")
    public ResponseEntity<String> confirmar(@PathVariable Long id, String retorno){

        var mensagem = notaFiscalEntradaService.alterarStatus(id, StatusNota.CONFIRMADA, "Nota fiscal de entrada confirmada.");

        return mensagem;
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<String> cancelar(@PathVariable Long id, String retorno){

        var mensagem = notaFiscalEntradaService.alterarStatus(id, StatusNota.CONFIRMADA, "Nota fiscal de entrada cancelada.");

        return mensagem;
    }
}
