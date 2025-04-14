package br.com.gestionna.creditosapi.service;

import br.com.gestionna.creditosapi.repository.CreditoRepository;
import br.com.gestionna.creditosapi.dto.CreditoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditoConsultaService {

    private final CreditoRepository repository;

    public CreditoConsultaService(CreditoRepository repository) {
        this.repository = repository;
    }

    public List<CreditoDTO> buscarPorNfse(String numeroNfse) {
        return repository.findByNumeroNfse(numeroNfse)
                .stream()
                .map(CreditoDTO::new)
                .toList();
    }

    public List<CreditoDTO> buscarPorNumeroCredito(String numeroCredito) {
        return repository.findByNumeroCredito(numeroCredito)
                .stream()
                .map(CreditoDTO::new)
                .toList();
    }

    public List<CreditoDTO> buscarTodos() {
        return repository.findAll()
                .stream()
                .map(CreditoDTO::new)
                .toList();
    }
}
