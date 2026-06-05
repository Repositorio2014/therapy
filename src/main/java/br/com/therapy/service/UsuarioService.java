package br.com.therapy.service;

import br.com.therapy.dto.UsuarioDTO;
import br.com.therapy.exception.BusinessException;
import br.com.therapy.mapper.UsuarioMapper;
import br.com.therapy.model.Usuario;
import br.com.therapy.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioDTO> findAll(){
        List<UsuarioDTO> usuarioListDTO = this.usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
        return usuarioListDTO;
    }

    public boolean usuarioEncontrado(Optional<UsuarioDTO> usuario){
        Optional<Usuario> user = this.usuarioRepository.findByUsername(usuario.get().getUsername());
        return user.isPresent();
    }

    public UsuarioDTO create(UsuarioDTO dto) {
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new BusinessException("Já existe usuário com esse login");
        }
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword())); // importante: criptografar senha
        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public Optional<Usuario> findUsuarioByUserName(String username){
        return this.usuarioRepository.findByUsername(username);
    }

    public Optional<Usuario> findUsuarioByCpf(String cpf){
        return this.usuarioRepository.findByCpf(cpf);
    }

    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        // Atualiza os campos
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setUsername(dto.getUsername());
        usuario.setRole(dto.getRole());

        // Atualiza senha se informada
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public boolean delete(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuarioRepository.delete(usuario);
            return true;
        }).orElse(false);
    }
}
