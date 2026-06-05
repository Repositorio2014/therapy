package br.com.therapy.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class UsuarioDTO{

        private Long id;

        @NotBlank(message = "Nome é obrigatório")
        private String nome;
        @NotBlank(message = "cpf é obrigatório")
        private String cpf;
        @NotBlank(message = "Login é obrigatório")
        private String username;
        @NotBlank(message = "Senha é obrigatória")
        @JsonIgnore
        private String password;
        private String role;
}