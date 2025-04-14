package br.com.gestionna.creditosapi.controller;

import br.com.gestionna.creditosapi.entity.Credito;
import br.com.gestionna.creditosapi.repository.CreditoRepository;
import br.com.gestionna.creditosapi.service.CreditoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.MockitoAnnotations;

class CreditoConsultaControllerTest {

    @Mock
    private CreditoRepository repository;

    @InjectMocks
    private CreditoConsultaController controller;

    private Credito credito;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        credito = new Credito();
        credito.setNumeroCredito("123456");
        credito.setNumeroNfse("7891011");
        credito.setDataConstituicao(LocalDate.of(2024, 2, 25));
        credito.setValorIssqn(new BigDecimal("1500.75"));
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("5.0"));
        credito.setValorFaturado(new BigDecimal("30000.00"));
        credito.setValorDeducao(new BigDecimal("5000.00"));
        credito.setBaseCalculo(new BigDecimal("25000.00"));
    }

    @Test
    void testBuscarCreditoPorNfse() {
        when(repository.findByNumeroNfse("7891011")).thenReturn(List.of(credito));

        List<CreditoService> result = controller.buscarCreditoPorNfse("7891011");

        assertEquals(1, result.size());
        assertEquals("123456", result.get(0).numeroCredito());
    }

    @Test
    void testBuscarCreditoPorNumero() {
        when(repository.findByNumeroCredito("123456")).thenReturn(List.of(credito));

        List<CreditoService> result = controller.buscarCreditoPorNumero("123456");

        assertEquals(1, result.size());
        assertEquals("7891011", result.get(0).numeroNfse());
    }

    @Test
    void testBuscaCreditos() {
        when(repository.findAll()).thenReturn(List.of(credito));

        List<CreditoService> result = controller.buscaCreditos();

        assertFalse(result.isEmpty());
        assertEquals("ISSQN", result.get(0).tipoCredito());
    }
}
