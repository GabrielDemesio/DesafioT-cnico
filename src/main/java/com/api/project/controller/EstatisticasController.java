package com.api.project.controller;

import com.api.project.business.services.EstatisticasService;
import com.api.project.controller.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
@Slf4j
public class EstatisticasController {
    //private final TransacaoService transacaoService;
    private final EstatisticasService estatisticasService;


    @GetMapping
    @Operation(description = "Endpoint Responsável por buscar estatisticas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Busca efetuada    "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(
                estatisticasService.calcularEstatisticas(intervaloBusca));
    }
}
