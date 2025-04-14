package br.com.gestionna.creditosapi.service;

import br.com.gestionna.creditosapi.dto.CreditoDTO;
import br.com.gestionna.creditosapi.entity.Credito;
import br.com.gestionna.creditosapi.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CreditoConsultaServiceTest {

    private CreditoRepository repository;
    private CreditoConsultaService service;

    @BeforeEach
    void setup() {
        repository = mock(CreditoRepository.class);
        service = new CreditoConsultaService(repository);
    }

    @Test
    void deveRetornarCreditoPorNfse() {
        // Arrange
        Credito credito = criarCreditoMock();
        when(repository.findByNumeroNfse("NF123")).thenReturn(List.of(credito));

        // Act
        List<CreditoDTO> resultado = service.buscarPorNfse("NF123");

        // Assert
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).numeroNfse()).isEqualTo("NF123");
        verify(repository, times(1)).findByNumeroNfse("NF123");
    }

    private Credito criarCreditoMock() {
        Credito c = new Credito();
        c.setNumeroCredito("C123");
        c.setNumeroNfse("NF123");
        c.setDataConstituicao(LocalDate.now());
        c.setValorIssqn(BigDecimal.TEN);
        c.setTipoCredito("ISSQN");
        c.setSimplesNacional(true);
        c.setAliquota(new BigDecimal("5.0"));
        c.setValorFaturado(new BigDecimal("1000.00"));
        c.setValorDeducao(new BigDecimal("200.00"));
        c.setBaseCalculo(new BigDecimal("800.00"));
        return c;
    }
}
