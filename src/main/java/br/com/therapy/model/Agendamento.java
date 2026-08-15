package br.com.therapy.model;

import br.com.therapy.enumeration.StatusAgendamento;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // cada agendamento tem 1 paciente
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(optional = false) // cada agendamento tem 1 profissional
    @JoinColumn(name = "profissional_id")
    private Funcionario profissional;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status; // pode ser PENDENTE, CONFIRMADO, CANCELADO etc.

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

}

