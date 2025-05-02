package com.api.project.controller;


import com.api.project.business.services.TransacaoService;
import com.api.project.controller.dtos.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(description = "Endpoint Responsável por adicionar transação")
    @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Transação gravada"),
          @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
          @ApiResponse(responseCode = "400", description = "Erro de requisição"),
          @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO) {
        transacaoService.adcionarTransacao(transacaoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping
    @Operation(description = "Endpoint Responsável por deletar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação deletada"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> removerTransacao() {
        transacaoService.limparTransacao();
        return ResponseEntity.ok().build();
    }
}
