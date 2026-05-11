package br.com.therapy.repository;

import br.com.therapy.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByNome(String nome); // exemplo de validação simples
}