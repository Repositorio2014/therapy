package br.com.therapy.controller;

import br.com.therapy.dto.UsuarioDTO;
import br.com.therapy.model.Usuario;
import br.com.therapy.repository.UsuarioRepository;
import br.com.therapy.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController( UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioDTO usuario) throws Exception {

        if (this.usuarioService.usuarioEncontrado(Optional.ofNullable(usuario))) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409 Conflict
                    .body("Usuário já cadastrado com esse login");
        }

        if (usuario == null) throw new AssertionError();
        UsuarioDTO novoUsuario = this.usuarioService.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
}