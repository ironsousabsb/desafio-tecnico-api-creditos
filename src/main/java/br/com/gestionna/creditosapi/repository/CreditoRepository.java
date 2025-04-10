package br.com.gestionna.creditosapi.repository;

import br.com.gestionna.creditosapi.entity.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {
    List<Credito> findByNumeroNfse(String numeroNfse);
    List<Credito> findByNumeroCredito(String numeroCredito);
}
