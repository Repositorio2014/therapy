package br.com.therapy.dto;

import br.com.therapy.model.CategoriaFuncionario;
import br.com.therapy.model.Endereco;

public record FuncionarioDTO(
        Long id,
        String nome,
        String cpf,
        Endereco endereco,
        CategoriaFuncionario categoria,
        String fone,
        String email
) {
}
