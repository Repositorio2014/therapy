package br.com.therapy.controller;

import br.com.therapy.config.JwtUtil;
import br.com.therapy.dto.LoginResponse;
import br.com.therapy.model.Usuario;
import br.com.therapy.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> creds) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.get("username"),
                        creds.get("password")
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        Optional<Usuario> usuarioOpt = this.usuarioService.findUsuarioByUserName(creds.get("username"));

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsername(userDetails.getUsername());
        response.setRoles(userDetails.getAuthorities());
        usuarioOpt.ifPresent(response::setUsuario);

        return ResponseEntity.ok(response);
        //return ResponseEntity.ok(Map.of("token", token));
    }

}
