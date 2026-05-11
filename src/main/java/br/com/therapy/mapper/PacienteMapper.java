package br.com.therapy.mapper;

import br.com.therapy.dto.PacienteDTO;
import br.com.therapy.enumeration.TipoPlano;
import br.com.therapy.model.Paciente;

public class PacienteMapper {

    public static Paciente toEntity(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setDataNascimento(dto.dataNascimento());
        paciente.setIdade(dto.idade());
        paciente.setEndereco(dto.endereco());
        paciente.setResponsaveis(dto.responsaveis());
        paciente.setPlano(TipoPlano.valueOf(dto.plano().toUpperCase()));
        return paciente;
    }

    public static PacienteDTO toDTO(Paciente paciente) {
        return new PacienteDTO(
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getIdade(),
                paciente.getEndereco(),
                paciente.getResponsaveis(),
                paciente.getPlano().name()
        );
    }
}