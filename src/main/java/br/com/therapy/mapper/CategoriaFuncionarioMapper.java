package br.com.therapy.mapper;

import br.com.therapy.dto.CategoriaFuncionarioDTO;
import br.com.therapy.model.CategoriaFuncionario;

public class CategoriaFuncionarioMapper {
    public static CategoriaFuncionario toEntity(CategoriaFuncionarioDTO dto){
        return CategoriaFuncionario.builder()
                .id(dto.id())
                .nome(dto.nome())
                .funcionarios(dto.funcionarios())
                .build();
    }

    public static CategoriaFuncionarioDTO toDTO(CategoriaFuncionario categoria){
        return new CategoriaFuncionarioDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getFuncionarios()
        );
    }
}
