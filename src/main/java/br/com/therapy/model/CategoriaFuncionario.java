package br.com.therapy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class CategoriaFuncionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Date dtCriacao = new Date();
    private Date dtAlteracao;

    @OneToMany(mappedBy = "categoria")
    @JsonBackReference
    private List<Funcionario> funcionarios;

    public CategoriaFuncionario() {
    }
}
