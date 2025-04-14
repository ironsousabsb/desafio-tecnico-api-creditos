package br.com.gestionna.creditosapi.controller;

import br.com.gestionna.creditosapi.dto.CreditoDTO;
import br.com.gestionna.creditosapi.service.CreditoConsultaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditoConsultaController.class)
@Import(CreditoConsultaControllerTest.MockConfig.class)
public class CreditoConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreditoConsultaService service;

    @Test
    void deveRetornarCreditoPorNfse() throws Exception {
        CreditoDTO dto = new CreditoDTO("C001", "NF001", LocalDate.of(2024, 1, 1),
                new BigDecimal("100.00"), "ISSQN", true,
                new BigDecimal("3.5"), new BigDecimal("1000.00"),
                new BigDecimal("200.00"), new BigDecimal("800.00"));

        when(service.buscarPorNfse("NF001")).thenReturn(List.of(dto));

        mockMvc.perform(get("/creditos/nfse/NF001")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroNfse").value("NF001"))
                .andExpect(jsonPath("$[0].numeroCredito").value("C001"));
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        public CreditoConsultaService creditoConsultaService() {
            return Mockito.mock(CreditoConsultaService.class);
        }
    }
}
