package br.com.therapy.mapper;

import br.com.therapy.dto.PacienteDTO;
import br.com.therapy.dto.PacienteRequestDTO;
import br.com.therapy.enumeration.TipoPlano;
import br.com.therapy.model.Paciente;
import br.com.therapy.model.Plano;

import java.util.Date;

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

    public static Paciente toEntity(PacienteRequestDTO dto, Plano plano) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setIdade(dto.getIdade());
        paciente.setEndereco(EnderecoMapper.toEntity(dto.getEndereco()));
        paciente.setResponsaveis(dto.getResponsaveis());
        paciente.setTipoPlano(TipoPlano.valueOf(dto.getTipoPlano()));
        paciente.setPlano(plano); // aqui pode ser null
        paciente.setDtCriacao(new Date());
        return paciente;
    }


    public static PacienteDTO toDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO(
                paciente.getId(),
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

        if (paciente.getPlano() != null) {
            dto.setPlanoId(paciente.getPlano().getId());
            dto.setPlanoNome(paciente.getPlano().getNome());
        }
        return dto;

    }

/*    public PacienteDTO fromRequestToDTO(PacienteRequestDTO request){
        PacienteDTO dto = new PacienteDTO(
                request.getNome(),
                request.getDataNascimento(),
                request.getIdade(),
                EnderecoMapper.toDTO(request.getEndereco()),
                request.getResponsaveis(),
                request.getTipoPlano(),
                request.getPlano(),
                request.getDtCriacao(),
                request.getDtAlteracao()
        );

        if (request.getPlano() != null) {
            dto.setPlanoId(request.getPlano().getId());
            dto.setPlanoNome(request.getPlano().getNome());
        }
        return dto;
    }*/
}