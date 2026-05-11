package br.com.therapy.mapper;

import br.com.therapy.dto.UsuarioDTO;
import br.com.therapy.model.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioDTO dto) {
        return Usuario.builder()
                .id(dto.id())
                .username(dto.nome())
                .password(dto.senha())
                .role(dto.role())
                .build();
    }

    public static UsuarioDTO toDTO(Usuario usuario){
        return new UsuarioDTO(usuario.getId(), usuario.getUsername(), usuario.getUsername(), usuario.getPassword(), usuario.getRole());
    }
}
