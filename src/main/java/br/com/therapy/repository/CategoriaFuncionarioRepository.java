package br.com.therapy.repository;

import br.com.therapy.model.CategoriaFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaFuncionarioRepository extends JpaRepository<CategoriaFuncionario, Long> {
}
