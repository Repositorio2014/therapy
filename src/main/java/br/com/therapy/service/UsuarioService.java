package br.com.therapy.service;

import br.com.therapy.dto.UsuarioDTO;
import br.com.therapy.exception.BusinessException;
import br.com.therapy.mapper.UsuarioMapper;
import br.com.therapy.model.Usuario;
import br.com.therapy.repository.EnderecoRepository;
import br.com.therapy.repository.UsuarioRepository;
import br.com.therapy.response.UsuarioResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, EnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponse> findAll(){
        List<UsuarioResponse> usuarioListResp = this.usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
        return usuarioListResp;
    }

    public boolean usuarioEncontrado(Optional<UsuarioDTO> usuario){
        Optional<Usuario> user = this.usuarioRepository.findByUsername(usuario.get().getUsername());
        return user.isPresent();
    }

    public UsuarioResponse create(UsuarioDTO dto) {
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new BusinessException("Já existe usuário com esse login");
        }
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword())); // importante: criptografar senha

        if(usuario.getDtCriacao() == null) {
            usuario.setDtCriacao(new Date());
        }

        return UsuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    public Optional<Usuario> findUsuarioByUserName(String username){
        return this.usuarioRepository.findByUsername(username);
    }

    public Optional<Usuario> findUsuarioByCpf(String cpf){
        return this.usuarioRepository.findByCpf(cpf);
    }

    public UsuarioResponse update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        // Atualiza os campos
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setUsername(dto.getUsername());
        usuario.setRole(dto.getRole());
        usuario.setDtAlteracao(new Date());

        // Atualiza senha se informada
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return UsuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    public boolean delete(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuarioRepository.delete(usuario);
            return true;
        }).orElse(false);
    }
}
