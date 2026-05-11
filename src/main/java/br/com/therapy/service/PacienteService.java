package br.com.therapy.service;

import br.com.therapy.dto.PacienteDTO;
import br.com.therapy.exception.BusinessException;
import br.com.therapy.exception.ResourceNotFoundException;
import br.com.therapy.mapper.PacienteMapper;
import br.com.therapy.model.Paciente;
import br.com.therapy.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PacienteDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));
        return PacienteMapper.toDTO(paciente);
    }

    public PacienteDTO criar(PacienteDTO dto) {
        if (pacienteRepository.existsByNome(dto.nome())) {
            throw new BusinessException("Já existe paciente com esse nome");
        }
        Paciente paciente = PacienteMapper.toEntity(dto);
        return PacienteMapper.toDTO(pacienteRepository.save(paciente));
    }

    public PacienteDTO atualizar(Long id, PacienteDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));

        paciente.setNome(dto.nome());
        paciente.setDataNascimento(dto.dataNascimento());
        paciente.setIdade(dto.idade());
        paciente.setEndereco(dto.endereco());
        paciente.setResponsaveis(dto.responsaveis());
        paciente.setPlano(PacienteMapper.toEntity(dto).getPlano());

        return PacienteMapper.toDTO(pacienteRepository.save(paciente));
    }

    public void deletar(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));
        pacienteRepository.delete(paciente);
    }
}