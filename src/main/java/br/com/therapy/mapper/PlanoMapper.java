package br.com.therapy.mapper;

import br.com.therapy.dto.PlanoDTO;
import br.com.therapy.model.Plano;

public class PlanoMapper {

    public static Plano toEntity(PlanoDTO dto) {
        Plano plano = new Plano();
        plano.setId(dto.getId());
        plano.setNome(dto.getNome());
        return plano;
    }

    public static PlanoDTO toDTO(Plano plano) {
        return new PlanoDTO(
                plano.getId(),
                plano.getNome()
        );
    }

}
