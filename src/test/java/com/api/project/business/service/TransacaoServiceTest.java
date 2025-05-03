package com.api.project.business.service;

import com.api.project.business.services.TransacaoService;
import com.api.project.controller.dtos.EstatisticasResponseDTO;
import com.api.project.controller.dtos.TransacaoRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    private TransacaoRequestDTO transacaoRequestDTO;

    @BeforeEach
    public void setUp() {
        transacaoRequestDTO = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        new EstatisticasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void deveAdicionarTransacaoComSucesso() {
        transacaoService.adcionarTransacao(transacaoRequestDTO);
        List<TransacaoRequestDTO>  transacoes = transacaoService.buscarTransacao(5000);
        assertTrue(transacoes.contains(transacaoRequestDTO));
    }

}
