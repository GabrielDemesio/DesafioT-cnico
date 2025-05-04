package com.api.project.business.service;

import com.api.project.business.services.TransacaoService;
import com.api.project.controller.dtos.EstatisticasResponseDTO;
import com.api.project.controller.dtos.TransacaoRequestDTO;
import com.api.project.infra.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    void deveAdicionarTransacaoComErro() {
        UnprocessableEntity exception =  assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adcionarTransacao(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));
        assertEquals("Valor não pode ser menor que 0 ",  exception.getMessage());
    }
    @Test
    void exceptionDataHoraMaiorQueAtual(){
        UnprocessableEntity exception =  assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adcionarTransacao(new TransacaoRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));
        assertEquals("Data e hora maior que atual ",  exception.getMessage());
    }
    @Test
    void deveLimparTransacao() {
        transacaoService.limparTransacao();
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacao(5000);
        assertTrue(transacoes.isEmpty());
    }
    @Test
    void deveLimparTransacaoComErro() {
        TransacaoRequestDTO requestDTO = new TransacaoRequestDTO(10.0, OffsetDateTime.now().minusHours(1));
        transacaoService.adcionarTransacao(transacaoRequestDTO);
        transacaoService.adcionarTransacao(requestDTO);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacao(60);
        assertTrue(transacoes.contains(transacaoRequestDTO));
        assertTrue(transacoes.contains(requestDTO));
    }

}
