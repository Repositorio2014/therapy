package br.com.therapy.service;

import br.com.therapy.dto.FuncionarioDTO;
import br.com.therapy.mapper.CategoriaFuncionarioMapper;
import br.com.therapy.mapper.FuncionarioMapper;
import br.com.therapy.model.Endereco;
import br.com.therapy.model.Funcionario;
import br.com.therapy.model.Usuario;
import br.com.therapy.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private static final Logger log = LoggerFactory.getLogger(FuncionarioService.class);
    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoService enderecoService;
    private final UsuarioService usuarioService;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, EnderecoService enderecoService, UsuarioService usuarioService) {
        this.funcionarioRepository = funcionarioRepository;
        this.enderecoService = enderecoService;
        this.usuarioService = usuarioService;
    }

    public List<FuncionarioDTO> listarTodos() {
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        List<FuncionarioDTO> funcionarioDtoList = new ArrayList<>();
        funcionarioList.forEach(
                f -> {
                    funcionarioDtoList.add(FuncionarioMapper.toDTO(f));
                }
        );
        return funcionarioDtoList;
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public FuncionarioDTO salvar(FuncionarioDTO funcionarioDTO) {
        // se vier apenas o CEP, busca no ViaCEP
        /*if (funcionario.endereco() != null && funcionario.endereco().getCep() != null) {
            Endereco enderecoCompleto = enderecoService.buscarPorCep(funcionario.endereco().getCep());
            funcionario.endereco(enderecoCompleto);
        }*/
        log.info("Started process of saving Funcionario, with {}", funcionarioDTO);

        Endereco enderecoCompleto = enderecoService.buscarPorCep(funcionarioDTO.getEndereco().getCep());

        if(!funcionarioDTO.getUsuario().getCpf().equals(funcionarioDTO.getCpf())) {
            log.info("Os cpf's estão diferentes {}", funcionarioDTO.getUsuario().getCpf());
            return null;
        }
        Optional<Usuario> userOpt = this.usuarioService.findUsuarioByCpf(funcionarioDTO.getUsuario().getCpf());
        if (haveCpf(userOpt)) return null;

        Funcionario funcionario = new Funcionario(
                funcionarioDTO.getId(),
                funcionarioDTO.getCpf(),
                enderecoCompleto,
                CategoriaFuncionarioMapper.toEntity(funcionarioDTO.getCategoria()),
                funcionarioDTO.getFone(),
                funcionarioDTO.getEmail()
        );
        funcionarioRepository.save(funcionario);
        return FuncionarioMapper.toDTO(funcionario);
    }

    private static boolean haveCpf(Optional<Usuario> userOpt) {
        if (userOpt.isPresent() && (userOpt.get().getCpf() == null || userOpt.get().getCpf().isEmpty())) {
            log.error("For insertion of Funcionario, must be provided a CPF registered.");
            return true;
        }
        return false;
    }

    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionario.setCpf(funcionarioAtualizado.getCpf());
                    funcionario.setCategoria(funcionarioAtualizado.getCategoria());
                    funcionario.setFone(funcionarioAtualizado.getFone());
                    funcionario.setEmail(funcionarioAtualizado.getEmail());

                    if (funcionarioAtualizado.getEndereco() != null &&
                            funcionarioAtualizado.getEndereco().getCep() != null) {
                        Endereco enderecoCompleto = enderecoService.buscarPorCep(funcionarioAtualizado.getEndereco().getCep());
                        funcionario.setEndereco(enderecoCompleto);
                    }

                    return funcionarioRepository.save(funcionario);
                })
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public void deletar(Long id) {
        funcionarioRepository.deleteById(id);
    }
}