package com.api.project.business.services;

import com.api.project.controller.dtos.EstatisticasResponseDTO;
import com.api.project.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticas(Integer intervaloBusca) {
        log.info("Calculando Estatisticas");
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacao(intervaloBusca);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();
        log.info("Estatisticas retornadas: {}", estatisticasTransacoes);
        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
