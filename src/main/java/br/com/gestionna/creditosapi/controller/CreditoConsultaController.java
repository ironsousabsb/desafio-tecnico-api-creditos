package br.com.gestionna.creditosapi.controller;

import br.com.gestionna.creditosapi.dto.CreditoDTO;
import br.com.gestionna.creditosapi.service.CreditoConsultaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditos")
public class CreditoConsultaController {

    private final CreditoConsultaService creditoService;

    public CreditoConsultaController(CreditoConsultaService creditoService) {
        this.creditoService = creditoService;
    }

    @GetMapping("/nfse/{numeroNfse}")
    public List<CreditoDTO> buscarCreditoPorNfse(@PathVariable String numeroNfse) {
        return creditoService.buscarPorNfse(numeroNfse);
    }

    @GetMapping("/numero/{numeroCredito}")
    public List<CreditoDTO> buscarCreditoPorNumero(@PathVariable String numeroCredito) {
        return creditoService.buscarPorNumeroCredito(numeroCredito);
    }

    @GetMapping
    public List<CreditoDTO> buscarTodosCreditos() {
        return creditoService.buscarTodos();
    }
}
