package br.com.therapy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@Table
@Builder
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade; // cidade
    private String uf;         // estado
    private Date dtCriacao = new Date();
    private Date dtAlteracao;

    /*@OneToOne(mappedBy = "endereco")
    private Paciente paciente;*/

    @OneToOne(mappedBy = "endereco")
    @JsonBackReference
    private Funcionario funcionario;

    public Endereco(String cep, String logradouro, String complemento, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public Endereco(String cep, String logradouro, String complemento, String bairro, String localidade, String uf, Date dtCriacao, Date dtAlteracao) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.dtCriacao = dtCriacao;
        this.dtAlteracao = dtAlteracao;
    }

    public Endereco() {

    }
}