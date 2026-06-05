package br.com.therapy.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaFuncionarioDTO{
    private Long id;
    private String nome;
    private Date dtCriacao;
    private Date dtAlteracao;
    //private List<FuncionarioDTO> funcionarios;
}
