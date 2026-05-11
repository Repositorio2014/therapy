package br.com.therapy.service;

import br.com.therapy.dto.FuncionarioDTO;
import br.com.therapy.mapper.FuncionarioMapper;
import br.com.therapy.model.Endereco;
import br.com.therapy.model.Funcionario;
import br.com.therapy.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoService enderecoService;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, EnderecoService enderecoService) {
        this.funcionarioRepository = funcionarioRepository;
        this.enderecoService = enderecoService;
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
        Endereco enderecoCompleto = enderecoService.buscarPorCep(funcionarioDTO.endereco().getCep());

        Funcionario funcionario = new Funcionario(
                funcionarioDTO.id(),
                funcionarioDTO.nome(),
                funcionarioDTO.cpf(),
                enderecoCompleto, // novo endereço
                funcionarioDTO.categoria(),
                funcionarioDTO.fone(),
                funcionarioDTO.email()
        );
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionario.setNome(funcionarioAtualizado.getNome());
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