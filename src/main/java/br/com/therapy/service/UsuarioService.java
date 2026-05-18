package br.com.therapy.service;

import br.com.therapy.dto.UsuarioDTO;
import br.com.therapy.exception.BusinessException;
import br.com.therapy.mapper.UsuarioMapper;
import br.com.therapy.model.Usuario;
import br.com.therapy.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean usuarioEncontrado(Optional<UsuarioDTO> usuario){
        Optional<Usuario> user = this.usuarioRepository.findByUsername(usuario.get().username());
        return user.isPresent();
    }

    /*public boolean usuarioEncontrado(Optional<UsuarioDTO> usuario){
        Optional<Usuario> user = this.usuarioRepository.findByUsername(usuario.get().username());
        if(user.isPresent()){
            return true;
        }
        return false;
    }*/

    /*public UsuarioDTO create(UsuarioDTO dto) {
        if (usuarioRepository.existsById(dto.id())) {
            throw new BusinessException("Já existe usuário com esse login");
        }
        Usuario usuario = UsuarioMapper.toEntity(dto);
        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }*/

    public UsuarioDTO create(UsuarioDTO dto) {
        if (usuarioRepository.findByUsername(dto.username()).isPresent()) {
            throw new BusinessException("Já existe usuário com esse login");
        }
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.password())); // importante: criptografar senha
        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

}
