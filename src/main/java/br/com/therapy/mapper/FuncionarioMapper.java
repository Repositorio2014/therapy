package br.com.therapy.mapper;

import br.com.therapy.dto.FuncionarioDTO;
import br.com.therapy.model.Funcionario;

public class FuncionarioMapper {
    public static Funcionario toEntity(FuncionarioDTO dto){
        return new Funcionario(
                dto.getId(),
                dto.getCpf(),
                EnderecoMapper.toEntity(dto.getEndereco()),
                CategoriaFuncionarioMapper.toEntity(dto.getCategoria()),
                dto.getFone(),
                dto.getEmail(),
                UsuarioMapper.toEntity(dto.getUsuario()));
    }

    public static FuncionarioDTO toDTO(Funcionario funcionario){
        return new FuncionarioDTO(
                funcionario.getId(),
                funcionario.getCpf(),
                EnderecoMapper.toDTO(funcionario.getEndereco()),
                CategoriaFuncionarioMapper.toDTO(funcionario.getCategoria()),
                funcionario.getFone(),
                funcionario.getEmail(),
                funcionario.getUsuario()
        );
    }
}
