package br.com.therapy.mapper;

import br.com.therapy.dto.UsuarioDTO;
import br.com.therapy.model.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioDTO dto) {
        return Usuario.builder()
                .id(dto.id())
                .nome(dto.nome())
                .cpf(dto.cpf())
                .username(dto.username())
                .password(dto.password())
                .role(dto.role())
                .build();
    }

    public static UsuarioDTO toDTO(Usuario usuario){
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getUsername(), usuario.getPassword(), usuario.getRole());
    }
}
