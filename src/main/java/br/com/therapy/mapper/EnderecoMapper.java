package br.com.therapy.mapper;

import br.com.therapy.dto.EnderecoResponse;
import br.com.therapy.model.Endereco;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoResponse dto){
        return new Endereco(
                dto.getCep(),
                dto.getLogradouro(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getLocalidade(),
                dto.getUf());
    }

    public static EnderecoResponse toDTO(Endereco endereco){
        return new EnderecoResponse(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getLocalidade(),
                endereco.getUf());
    }

}
