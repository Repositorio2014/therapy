package br.com.therapy.service;

import br.com.therapy.dto.AgendamentoRequestDTO;
import br.com.therapy.dto.AgendamentoResponseDTO;
import br.com.therapy.mapper.AgendamentoMapper;
import br.com.therapy.model.Agendamento;
import br.com.therapy.model.Funcionario;
import br.com.therapy.model.Paciente;
import br.com.therapy.repository.AgendamentoRepository;
import br.com.therapy.repository.FuncionarioRepository;
import br.com.therapy.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final PacienteRepository pacienteRepository;
    private final FuncionarioRepository funcionarioRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              PacienteRepository pacienteRepository,
                              FuncionarioRepository funcionarioRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.pacienteRepository = pacienteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<AgendamentoResponseDTO> listarTodos() {
        return agendamentoRepository.findAll()
                .stream()
                .map(AgendamentoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<AgendamentoResponseDTO> buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .map(AgendamentoMapper::toResponse);
    }

    public AgendamentoResponseDTO salvar(AgendamentoRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteiD())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        Funcionario profissional = funcionarioRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        Agendamento agendamento = new Agendamento();
        agendamento.setPaciente(paciente);
        agendamento.setProfissional(profissional);
        agendamento.setDataHora(dto.getDataHora());
        agendamento.setStatus(br.com.therapy.enumeration.StatusAgendamento.PENDENTE);
        agendamento.setDataCriacao(LocalDateTime.now());

        return AgendamentoMapper.toResponse(agendamentoRepository.save(agendamento));
    }

    public AgendamentoResponseDTO atualizar(Long id, AgendamentoRequestDTO dto) {
        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    Paciente paciente = pacienteRepository.findById(dto.getPacienteiD())
                            .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
                    Funcionario profissional = funcionarioRepository.findById(dto.getProfissionalId())
                            .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

                    agendamento.setPaciente(paciente);
                    agendamento.setProfissional(profissional);
                    agendamento.setDataHora(dto.getDataHora());
                    agendamento.setDataAtualizacao(LocalDateTime.now());

                    return AgendamentoMapper.toResponse(agendamentoRepository.save(agendamento));
                })
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public void deletar(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
