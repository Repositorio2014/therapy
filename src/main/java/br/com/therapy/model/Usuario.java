package br.com.therapy.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String username;
    private String password;
    private String role;
    private Date dtCriacao = new Date();
    private Date dtAlteracao;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Funcionario funcionario;

    // getters e setters
}