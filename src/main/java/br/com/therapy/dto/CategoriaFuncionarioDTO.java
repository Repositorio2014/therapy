package br.com.therapy.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaFuncionarioDTO{
    private Long id;
    private String nome;
    //private List<FuncionarioDTO> funcionarios;
}
