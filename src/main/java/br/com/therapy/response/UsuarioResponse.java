package br.com.therapy.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class UsuarioResponse {
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
    private Date dtCriacao;
    private Date dtAlteracao;
}
