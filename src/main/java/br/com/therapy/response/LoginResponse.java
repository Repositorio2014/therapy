package br.com.therapy.response;

import br.com.therapy.dto.UsuarioDTO;
import br.com.therapy.model.Usuario;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String username;
    private Object roles;
    private UsuarioDTO usuario;
}