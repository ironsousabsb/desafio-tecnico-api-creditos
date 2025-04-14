package br.com.gestionna.creditosapi.controller;

import br.com.gestionna.creditosapi.entity.Credito;
import br.com.gestionna.creditosapi.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditoConsultaControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CreditoRepository repository;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/creditos";
    }

    @BeforeEach
    public void setup() {
        repository.deleteAll(); // Limpa o banco de dados antes de cada teste

        // Insere dados de teste
        Credito credito = new Credito();
        credito.setNumeroCredito("12345");
        credito.setNumeroNfse("NF-2024");
        credito.setDataConstituicao(LocalDate.of(2024, 10, 1));
        credito.setValorIssqn(new BigDecimal("150.00"));
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("5.00"));
        credito.setValorFaturado(new BigDecimal("3000.00"));
        credito.setValorDeducao(new BigDecimal("500.00"));
        credito.setBaseCalculo(new BigDecimal("2500.00"));

        repository.save(credito);
    }

    @Test
    public void deveRetornarCreditoPorNumeroNfse() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                getBaseUrl() + "/nfse/NF-2024", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("NF-2024");
    }

    @Test
    public void deveRetornarCreditoPorNumeroCredito() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                getBaseUrl() + "/numero/12345", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("12345");
    }

    @Test
    public void deveRetornarTodosOsCreditos() {
        ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("12345", "NF-2024");
    }
}
