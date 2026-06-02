package br.com.therapy.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        Long id,
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "cpf é obrigatório")
        String cpf,
        @NotBlank(message = "Login é obrigatório")
        String username,
        @NotBlank(message = "Senha é obrigatória")
        String password,
        String role
) {}