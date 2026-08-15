package br.com.therapy.dto;

import br.com.therapy.enumeration.StatusAgendamento;
import br.com.therapy.model.Funcionario;
import br.com.therapy.model.Paciente;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoResponseDTO {
    private Paciente paciente;
    private Funcionario funcionario;
    private StatusAgendamento status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
