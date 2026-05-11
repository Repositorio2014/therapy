package br.com.therapy.model;

import br.com.therapy.enumeration.TipoPlano;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private Integer idade;

    // Relacionamento com a entidade Endereco
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    // Lista de responsáveis (se menor de idade)
    @ElementCollection
    @CollectionTable(name = "responsaveis_paciente", joinColumns = @JoinColumn(name = "paciente_id"))
    @Column(name = "responsavel")
    private List<String> responsaveis;

    // Plano de saúde ou particular
    @Enumerated(EnumType.STRING)
    private TipoPlano plano;

    // Getters e Setters
}
