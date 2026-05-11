package br.com.therapy.dto;

import br.com.therapy.model.Funcionario;

import java.util.List;

public record CategoriaFuncionarioDTO(
        Long id,
        String nome,
        List<Funcionario> funcionarios
) {
}
