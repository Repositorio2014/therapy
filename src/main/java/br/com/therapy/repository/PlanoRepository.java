package br.com.therapy.repository;

import br.com.therapy.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanoRepository extends JpaRepository<Plano, Long> {
    Optional<Plano> findPlanoById(Long id);
}
