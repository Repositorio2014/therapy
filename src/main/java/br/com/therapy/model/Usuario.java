package br.com.therapy.model;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Funcionario funcionario;

    // getters e setters
}