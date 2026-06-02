package br.com.therapy.model;

import br.com.therapy.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Funcionario(Long id, String cpf, Endereco enderecoCompleto, CategoriaFuncionario entity, String fone, String email) {
        this.id = id;
        this.cpf = cpf;
        this.endereco = enderecoCompleto;
        this.categoria = entity;
        this.fone = fone;
        this.email = email;
    }

    public Funcionario(Long id, String cpf, Endereco endereco, CategoriaFuncionario categoria, String fone, String email, Usuario usuario) {
        this.id = id;
        this.cpf = cpf;
        this.endereco = endereco;
        this.categoria = categoria;
        this.fone = fone;
        this.email = email;
        this.usuario = usuario;
    }


//    @ManyToMany(mappedBy = "funcionarios")
//    private List<Agendamento> agendamentos;
}
