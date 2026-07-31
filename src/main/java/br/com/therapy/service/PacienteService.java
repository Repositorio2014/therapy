package br.com.therapy.service;

import br.com.therapy.dto.PacienteDTO;
import br.com.therapy.dto.PacienteRequestDTO;
import br.com.therapy.exception.BusinessException;
import br.com.therapy.exception.ResourceNotFoundException;
import br.com.therapy.mapper.EnderecoMapper;
import br.com.therapy.mapper.PacienteMapper;
import br.com.therapy.mapper.PlanoMapper;
import br.com.therapy.model.Endereco;
import br.com.therapy.model.Paciente;
import br.com.therapy.model.Plano;
import br.com.therapy.repository.EnderecoRepository;
import br.com.therapy.repository.PacienteRepository;
import br.com.therapy.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PlanoRepository planoRepository;
    private final EnderecoRepository enderecoRepository;

    public PacienteService(PacienteRepository pacienteRepository, PlanoRepository planoRepository, EnderecoRepository enderecoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.planoRepository = planoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public List<PacienteDTO> listarTodos() {
        List<PacienteDTO> list = pacienteRepository.findAll()
                .stream()
                .map(PacienteMapper::toDTO)
                .collect(Collectors.toList());

        return list;
    }

    public PacienteDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));
        Optional<Endereco> endereco = enderecoRepository.findEnderecoById(paciente.getEndereco().getId());
        endereco.ifPresent(paciente::setEndereco);
        PacienteDTO pacienteDTO = PacienteMapper.toDTO(paciente);
        pacienteDTO.setEndereco(EnderecoMapper.toDTO(paciente.getEndereco()));
        return pacienteDTO;
    }

    public PacienteDTO criar(PacienteRequestDTO dto) {
        if (pacienteRepository.existsByNome(dto.getNome())) {
            throw new BusinessException("Já existe paciente com esse nome");
        }

        Plano plano = null;
        if (dto.getPlanoId() != null && dto.getPlanoId() > 0) {
            plano = planoRepository.findById(dto.getPlanoId())
                    .orElseThrow(() -> new BusinessException("Plano não encontrado"));
        }
        Paciente paciente = PacienteMapper.toEntity(dto, plano);
        paciente.setPlano(plano); // pode ser null


        // Seta o plano apenas se existir
        if (plano != null) {
            paciente.setPlano(plano);
        }

        // Define data de criação se não existir
        if (paciente.getDtCriacao() == null) {
            paciente.setDtCriacao(new Date());
        }

        // Persiste e retorna DTO
        return PacienteMapper.toDTO(pacienteRepository.save(paciente));
    }


    public PacienteDTO atualizar(Long id, PacienteRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));

        paciente.setNome(dto.getNome() != null ? dto.getNome() : paciente.getNome());
        paciente.setDataNascimento(dto.getDataNascimento() != null ? dto.getDataNascimento() : paciente.getDataNascimento());
        paciente.setIdade(dto.getIdade() != null ? dto.getIdade() : paciente.getIdade());
        paciente.setEndereco(dto.getEndereco() != null ? EnderecoMapper.toEntity(dto.getEndereco()) : paciente.getEndereco());
        paciente.setResponsaveis(dto.getResponsaveis() != null ? dto.getResponsaveis() : paciente.getResponsaveis());
        if(dto.getPlanoId() != null){
            Optional<Plano> plano = planoRepository.findPlanoById(dto.getPlanoId());
            plano.ifPresent(paciente::setPlano);
        }
        paciente.setDtAlteracao(dto.getDtAlteracao() != null ? dto.getDtAlteracao() : paciente.getDtAlteracao());

        return PacienteMapper.toDTO(pacienteRepository.save(paciente));
    }

    public void deletar(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));
        pacienteRepository.delete(paciente);
    }
}