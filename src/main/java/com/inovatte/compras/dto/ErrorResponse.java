package com.inovatte.compras.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String mensagem,
        LocalDateTime dataHora

) {

}
