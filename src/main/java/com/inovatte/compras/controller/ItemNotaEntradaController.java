package com.inovatte.compras.controller;

import com.inovatte.compras.dto.ItemNotaEntradaRequestDTO;
import com.inovatte.compras.dto.ItemNotaEntradaResponseDto;
import com.inovatte.compras.service.ItemNotaEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemnota")
public class ItemNotaEntradaController {

    @Autowired
    private ItemNotaEntradaService itemNotaEntradaService;

    @PostMapping
    public ResponseEntity<ItemNotaEntradaResponseDto> adicionar(@RequestBody ItemNotaEntradaRequestDTO dto){

        var itemNotaentrada = itemNotaEntradaService.adicionar(dto);

        return ResponseEntity.ok(itemNotaentrada);

    }
}
