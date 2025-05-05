package com.api.project.controller;

import com.api.project.business.services.TransacaoService;
import com.api.project.controller.dtos.TransacaoRequestDTO;
import com.api.project.infra.exceptions.UnprocessableEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransacaoController.class)
public class TransacaoControllerTest {
    @InjectMocks
    TransacaoController transacaoController;

    @Mock
    TransacaoService transacaoService;

    TransacaoRequestDTO transacaoRequestDTO;

    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
        transacaoRequestDTO = new TransacaoRequestDTO(20.0, OffsetDateTime.of(2025,2,18,14,30,0,0, ZoneOffset.UTC));
    }
    @Test
    void deveAdicionarTransacao() throws Exception {
        doNothing().when(transacaoService).adcionarTransacao(transacaoRequestDTO);
        mockMvc.perform(post("/transacao")
                .content(objectMapper.writeValueAsString(transacaoRequestDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    @Test
    void deveAdicionarTransacaoNulo() throws Exception {
        doThrow(new UnprocessableEntity("Erro de requisição")).when(transacaoService).adcionarTransacao(transacaoRequestDTO);
        ResultActions resultActions = mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transacaoRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    @Test
    void deveDeletarTransacao() throws Exception {
        doNothing().when(transacaoService).limparTransacao();
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }
}
