package com.api.project.controller;

import com.api.project.business.services.EstatisticasService;
import com.api.project.business.services.TransacaoService;
import com.api.project.controller.dtos.EstatisticasResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/estatistica")
@Slf4j
public class EstatisticasController {
    private final TransacaoService transacaoService;
    private final EstatisticasService estatisticasService;


    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticasService.calcularEstatisticas(intervaloBusca));
    }
}
