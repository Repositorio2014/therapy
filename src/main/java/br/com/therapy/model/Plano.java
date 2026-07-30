package br.com.therapy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Plano")
@Getter
@Setter
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    // Um plano pode ter vários pacientes
    @OneToMany(mappedBy = "plano")
    private List<Paciente> pacientes;
    Date dtCriacao;
    Date dtAlteracao;
}
