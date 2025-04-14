package br.com.gestionna.creditosapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreditoDTObkp(
        String numeroCredito,
        String numeroNfse,
        LocalDate dataConstituicao,
        BigDecimal valorIssqn,
        String tipoCredito,
        String simplesNacional,
        BigDecimal aliquota,
        BigDecimal valorFaturado,
        BigDecimal valorDeducao,
        BigDecimal baseCalculo
) {}
