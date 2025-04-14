package br.com.gestionna.creditosapi.service;

import br.com.gestionna.creditosapi.dto.CreditoDTO;
import br.com.gestionna.creditosapi.entity.Credito;
import br.com.gestionna.creditosapi.kafka.KafkaProducerService;
import br.com.gestionna.creditosapi.repository.CreditoRepository;
import br.com.gestionna.creditosapi.service.CreditoConsultaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

public class CreditoConsultaServiceTest {

    private CreditoRepository repository;
    private KafkaProducerService kafkaProducer;
    private CreditoConsultaService service;

    @BeforeEach
    void setup() {
        repository = mock(CreditoRepository.class);
        kafkaProducer = mock(KafkaProducerService.class);
        service = new CreditoConsultaService(repository, kafkaProducer);
    }

    @Test
    void deveRetornarCreditoPorNfse() {
        Credito credito = criarCreditoMock();
        when(repository.findByNumeroNfse("NF123")).thenReturn(List.of(credito));

        List<CreditoDTO> resultado = service.buscarPorNfse("NF123");

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).numeroNfse()).isEqualTo("NF123");
        verify(repository).findByNumeroNfse("NF123");
        verify(kafkaProducer).enviarMensagem("consulta-creditos", "Consulta por NFSe: NF123");
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
