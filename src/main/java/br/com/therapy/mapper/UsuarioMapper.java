package br.com.therapy.mapper;

import br.com.therapy.dto.UsuarioDTO;
import br.com.therapy.model.Usuario;
import br.com.therapy.response.UsuarioResponse;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioDTO dto) {
        return Usuario.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(dto.getRole())
                .dtCriacao(dto.getDtCriacao())
                .dtAlteracao(dto.getDtAlteracao())
                .build();
    }

    public static UsuarioDTO toDTO(Usuario usuario){
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getUsername(), usuario.getPassword(), usuario.getRole(), usuario.getDtCriacao(), usuario.getDtAlteracao());
    }

    public static UsuarioResponse toResponse(Usuario usuario){
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getUsername(), usuario.getPassword(), usuario.getRole(), usuario.getDtCriacao(), usuario.getDtAlteracao());
    }
}
