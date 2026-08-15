package br.com.therapy.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atendimentos")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "profissional_id")
    private Funcionario profissional;

    @Lob
    private String anamnese; // texto grande

    @Lob
    private String evolucao; // texto grande

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

}
