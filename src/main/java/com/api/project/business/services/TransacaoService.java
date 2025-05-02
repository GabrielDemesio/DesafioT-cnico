package com.api.project.business.services;

import com.api.project.controller.dtos.TransacaoRequestDTO;
import com.api.project.infra.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacao = new ArrayList<>();

    public void adcionarTransacao(TransacaoRequestDTO dto) {
        log.info("Adicionando transacao {}", dto);

        if (dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maior que atual");
            throw new UnprocessableEntity("Data de hora invalida");
        }
        if (dto.valor() < 0){
            log.error("valor maior que atual");
            throw new UnprocessableEntity("valor invalido");
        }
        listaTransacao.add(dto);
    }
    public void limparTransacao() {
        log.info("Limpando transacao");
        listaTransacao.clear();
        log.info("Limpando transacao finalizada");
    }
    public List<TransacaoRequestDTO> buscarTransacao(Integer intervaloBusca) {
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);
        log.info("Buscando transacao {}", dataHoraIntervalo);
        return listaTransacao.stream()
                .filter(transacoes -> transacoes.dataHora()
                        .isAfter(dataHoraIntervalo)).toList();
    }
}
