package br.com.therapy.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoRequestDTO {
    private long pacienteiD;
    private Long profissionalId;
    private LocalDateTime dataHora;
}
