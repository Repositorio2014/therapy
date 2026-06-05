package br.com.therapy.dto;

import br.com.therapy.model.Endereco;
import br.com.therapy.model.Usuario;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO{
    private Long id;
    private String cpf;
    private EnderecoResponse endereco;
    private CategoriaFuncionarioDTO categoria;
    private String fone;
    private String email;
    private UsuarioDTO usuario;
    private Date dtCriacao;
    private Date dtAlteracao;

    public FuncionarioDTO(Long id, String cpf, EnderecoResponse endereco, CategoriaFuncionarioDTO categoria, String fone, String email, Usuario usuario, Date dtCriacao, Date dtAlteracao) {
        this.id = id;
        this.cpf = cpf;
        this.endereco = endereco;
        this.categoria = categoria;
        this.fone = fone;
        this.email = email;
        this.dtCriacao = dtCriacao;
        this.dtAlteracao = dtAlteracao;
    }
}
