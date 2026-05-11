package br.com.therapy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

import br.com.therapy.model.Endereco;

public record PacienteDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Data de nascimento é obrigatória")
        @Past(message = "Data de nascimento deve estar no passado")
        LocalDate dataNascimento,

        Integer idade,

        @NotNull(message = "Endereço é obrigatório")
        Endereco endereco,

        List<String> responsaveis,

        @NotBlank(message = "Plano é obrigatório (PARTICULAR ou PLANO)")
        String plano
) {}
