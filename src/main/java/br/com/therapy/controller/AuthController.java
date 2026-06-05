package br.com.therapy.controller;

import br.com.therapy.config.JwtUtil;
import br.com.therapy.dto.LoginDTO;
import br.com.therapy.dto.UsuarioDTO;
import br.com.therapy.mapper.UsuarioMapper;
import br.com.therapy.response.LoginResponse;
import br.com.therapy.model.Usuario;
import br.com.therapy.service.UsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto) {
        logger.info("Entrada em login com {}", loginDto.getUsername());
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        Optional<Usuario> usuarioOpt = this.usuarioService.findUsuarioByUserName(loginDto.getUsername());

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsername(userDetails.getUsername());
        response.setRoles(userDetails.getAuthorities());

        usuarioOpt.ifPresent(usuario -> response.setUsuario(UsuarioMapper.toDTO(usuario)));

        logger.info("Response login {}", response.getToken());
        logger.info("Response login {}", response.getUsername());

        return ResponseEntity.ok(response);
    }

}
