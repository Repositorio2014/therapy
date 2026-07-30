package br.com.therapy.dto;

import br.com.therapy.model.Plano;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO{
        @NotBlank(message = "Nome é obrigatório")
        String nome;

        @NotNull(message = "Data de nascimento é obrigatória")
        @Past(message = "Data de nascimento deve estar no passado")
        LocalDate dataNascimento;

        Integer idade;

        @NotNull(message = "Endereço é obrigatório")
        EnderecoResponse endereco;

        List<String> responsaveis;

        @NotBlank(message = "Plano é obrigatório (PARTICULAR ou PLANO)")
        String tipoPlano;
        Plano plano;
        @JsonIgnore
        Date dtCriacao;
        @JsonIgnore
        Date dtAlteracao;

}
