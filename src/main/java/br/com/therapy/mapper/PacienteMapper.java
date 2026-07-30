package br.com.therapy.mapper;

import br.com.therapy.dto.PacienteDTO;
import br.com.therapy.enumeration.TipoPlano;
import br.com.therapy.model.Paciente;
import br.com.therapy.model.Plano;

public class PacienteMapper {

    public static Paciente toEntity(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        Plano plano = new Plano();
        plano.setId(dto.getPlano().getId());
        plano.setNome(dto.getPlano().getNome());
        plano.setPacientes(dto.getPlano().getPacientes());

        paciente.setNome(dto.getNome());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setIdade(dto.getIdade());
        paciente.setEndereco(EnderecoMapper.toEntity(dto.getEndereco()));
        paciente.setResponsaveis(dto.getResponsaveis());
        paciente.setTipoPlano(TipoPlano.valueOf(dto.getTipoPlano().toUpperCase()));
        paciente.setPlano(plano);
        paciente.setDtCriacao(dto.getDtCriacao());
        paciente.setDtAlteracao(dto.getDtAlteracao());
        return paciente;
    }

    public static PacienteDTO toDTO(Paciente paciente) {
        return new PacienteDTO(
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getIdade(),
                EnderecoMapper.toDTO(paciente.getEndereco()),
                paciente.getResponsaveis(),
                paciente.getTipoPlano().name(),
                paciente.getPlano(),
                paciente.getDtCriacao(),
                paciente.getDtAlteracao()
        );
    }
}