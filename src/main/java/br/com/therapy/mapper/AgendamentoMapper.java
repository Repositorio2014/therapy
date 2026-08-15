package br.com.therapy.mapper;

import br.com.therapy.dto.AgendamentoResponseDTO;
import br.com.therapy.model.Agendamento;

public class AgendamentoMapper {
    public static AgendamentoResponseDTO toResponse(Agendamento agendamento){
        return new AgendamentoResponseDTO(agendamento.getPaciente(),
                agendamento.getProfissional(),
                agendamento.getStatus(),
                agendamento.getDataCriacao(),
                agendamento.getDataAtualizacao());
    }
}
