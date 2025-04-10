package br.com.gestionna.creditosapi.service;

import br.com.gestionna.creditosapi.entity.Credito;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreditoService(String numeroCredito,
                             String numeroNfse,
                             LocalDate dataConstituicao,
                             BigDecimal valorIssqn,
                             String tipoCredito,
                             boolean  simplesNacional,
                             BigDecimal aliquota,
                             BigDecimal valorFaturado,
                             BigDecimal valorDeducao,
                             BigDecimal baseCalculo ) {

    public CreditoService(Credito credito){
        this(credito.getNumeroCredito(),
                credito.getNumeroNfse(),
                credito.getDataConstituicao(),
                credito.getValorIssqn(),
                credito.getTipoCredito(),
                credito.isSimplesNacional(),
                credito.getAliquota(),
                credito.getValorFaturado(),
                credito.getValorDeducao(),
                credito.getBaseCalculo());
    }
}
