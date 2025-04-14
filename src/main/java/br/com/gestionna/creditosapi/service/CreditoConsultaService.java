package br.com.gestionna.creditosapi.service;

import br.com.gestionna.creditosapi.dto.CreditoDTO;
import br.com.gestionna.creditosapi.kafka.KafkaProducerService;
import br.com.gestionna.creditosapi.repository.CreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditoConsultaService {

    private final CreditoRepository repository;
    private final KafkaProducerService kafkaProducer;

    public CreditoConsultaService(CreditoRepository repository, KafkaProducerService kafkaProducer) {
        this.repository = repository;
        this.kafkaProducer = kafkaProducer;
    }

    public List<CreditoDTO> buscarPorNfse(String numeroNfse) {
        var creditos = repository.findByNumeroNfse(numeroNfse);
        kafkaProducer.enviarMensagem("consulta-creditos", "Consulta por NFSe: " + numeroNfse);
        return creditos.stream().map(CreditoDTO::new).toList();
    }

    public List<CreditoDTO> buscarPorNumeroCredito(String numeroCredito) {
        var creditos = repository.findByNumeroCredito(numeroCredito);
        kafkaProducer.enviarMensagem("consulta-creditos", "Consulta por número do crédito: " + numeroCredito);
        return creditos.stream().map(CreditoDTO::new).toList();
    }

    public List<CreditoDTO> buscarTodos() {
        var creditos = repository.findAll();
        kafkaProducer.enviarMensagem("consulta-creditos", "Consulta de todos os créditos realizada.");
        return creditos.stream().map(CreditoDTO::new).toList();
    }
}
