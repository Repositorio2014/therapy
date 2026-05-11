package br.com.therapy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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

    /*@OneToOne(mappedBy = "endereco")
    private Paciente paciente;*/

    @OneToOne(mappedBy = "endereco")
    @JsonBackReference
    private Funcionario funcionario;


    public Endereco() {

    }
}