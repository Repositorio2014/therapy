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
        Long id;
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
        @JsonIgnore
        Plano plano;
        Long planoId;
        String planoNome;
        Date dtCriacao;
        Date dtAlteracao;

        public PacienteDTO(String nome,
                           LocalDate dataNascimento,
                           Integer idade,
                           EnderecoResponse endereco,
                           List<String> responsaveis,
                           String tipoPlano,
                           Plano plano,
                           Date dtCriacao,
                           Date dtAlteracao) {
                this.nome = nome;
                this.dataNascimento = dataNascimento;
                this.idade = idade;
                this.endereco = endereco;
                this.responsaveis = responsaveis;
                this.tipoPlano = tipoPlano;
                this.plano = plano;
                this.dtCriacao = dtCriacao;
                this.dtAlteracao = dtAlteracao;
        }

        public PacienteDTO(Long id,
                           String nome,
                           LocalDate dataNascimento,
                           Integer idade,
                           EnderecoResponse dto,
                           List<String> responsaveis,
                           String name,
                           Plano plano,
                           Date dtCriacao,
                           Date dtAlteracao) {
                this.id = id;
                this.nome = nome;
                this.dataNascimento = dataNascimento;
                this.idade = idade;
                this.endereco = endereco;
                this.responsaveis = responsaveis;
                this.tipoPlano = tipoPlano;
                this.plano = plano;
                this.dtCriacao = dtCriacao;
                this.dtAlteracao = dtAlteracao;
        }

        public Long getPlanoId() {
                return plano != null ? plano.getId() : null;
        }

        public String getPlanoNome() {
                return plano != null ? plano.getNome() : null;
        }

}
