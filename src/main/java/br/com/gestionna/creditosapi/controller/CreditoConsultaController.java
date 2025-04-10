package br.com.gestionna.creditosapi.controller;

import br.com.gestionna.creditosapi.entity.Credito;
import br.com.gestionna.creditosapi.repository.CreditoRepository;
import br.com.gestionna.creditosapi.service.CreditoService;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("creditos")
public class CreditoConsultaController {

    @Autowired
    private CreditoRepository repository;

    @GetMapping("/nfse/{numeroNfse}")
    public List<CreditoService> buscarCreditoPorNfse(@PathVariable String numeroNfse) {
        return repository.findByNumeroNfse(numeroNfse)
                .stream()
                .map(CreditoService::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/numero/{numeroCredito}")
    public List<CreditoService> buscarCreditoPorNumero(@PathVariable String numeroCredito) {
        return repository.findByNumeroCredito(numeroCredito)
                .stream()
                .map(CreditoService::new)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<CreditoService> buscaCreditos(){
        return repository.findAll().stream().map(CreditoService::new).toList();
    }
}
