package br.com.therapy.dto;

import br.com.therapy.model.Usuario;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String username;
    private Object roles;
    private Usuario usuario;
}