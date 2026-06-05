package br.com.therapy.controller;

import br.com.therapy.dto.UsuarioDTO;
import br.com.therapy.exception.BusinessException;
import br.com.therapy.model.Usuario;
import br.com.therapy.repository.UsuarioRepository;
import br.com.therapy.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuário inválido");
        }

        if (this.usuarioService.usuarioEncontrado(Optional.ofNullable(usuario))) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409 Conflict
                    .body("Usuário já cadastrado com este login");
        }

        if (usuario == null) throw new AssertionError();
        UsuarioDTO novoUsuario = this.usuarioService.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO atualizado = usuarioService.update(id, usuarioDTO);
            return ResponseEntity.ok(atualizado);
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        if (usuarioService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}