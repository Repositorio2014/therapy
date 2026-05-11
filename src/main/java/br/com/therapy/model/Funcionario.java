package br.com.therapy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @JsonManagedReference
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonManagedReference
    private CategoriaFuncionario categoria;

    private String fone;
    private String email;

    public Funcionario() {

    }

//    @ManyToMany(mappedBy = "funcionarios")
//    private List<Agendamento> agendamentos;
}
