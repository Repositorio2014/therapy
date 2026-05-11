package br.com.therapy.mapper;

import br.com.therapy.dto.FuncionarioDTO;
import br.com.therapy.model.Funcionario;

public class FuncionarioMapper {
    public static Funcionario toEntity(Funcionario dto){
        return Funcionario.builder()
        .id(dto.id())
                .nome(dto.nome())
                .cpf(dto.cpf())
                .endereco(dto.endereco())
                .categoria(dto.categoria())
                .fone(dto.fone())
                .email(dto.email())
                .build();
    }

    public static FuncionarioDTO toDTO(Funcionario funcionario){
        return new FuncionarioDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getEndereco(),
                funcionario.getCategoria(),
                funcionario.getFone(),
                funcionario.getEmail()
        );
    }
}
