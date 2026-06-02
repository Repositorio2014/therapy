package br.com.therapy.mapper;

import br.com.therapy.dto.CategoriaFuncionarioDTO;
import br.com.therapy.dto.FuncionarioDTO;
import br.com.therapy.model.CategoriaFuncionario;
import br.com.therapy.model.Funcionario;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaFuncionarioMapper {
    public static CategoriaFuncionario toEntity(CategoriaFuncionarioDTO dto){
        /*List<Funcionario> funcionarioList = dto.getFuncionarios()
                .stream()
                .map(FuncionarioMapper::toEntity)
                .collect(Collectors.toList());*/

        return CategoriaFuncionario.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .build();
    }

    public static CategoriaFuncionarioDTO toDTO(CategoriaFuncionario categoria){
        /*List<FuncionarioDTO> funcionarioDTOList = categoria.getFuncionarios()
                .stream()
                .map(FuncionarioMapper::toDTO)
                .collect(Collectors.toList());*/

        return new CategoriaFuncionarioDTO(
                categoria.getId(),
                categoria.getNome()
        );
    }
}
