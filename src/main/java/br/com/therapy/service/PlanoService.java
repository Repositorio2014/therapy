package br.com.therapy.service;

import br.com.therapy.dto.PlanoCreateDTO;
import br.com.therapy.dto.PlanoDTO;
import br.com.therapy.dto.PlanoRequest;
import br.com.therapy.model.Plano;
import br.com.therapy.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    // CREATE
    public PlanoDTO criar(String nome) {
        Plano plano = new Plano();
        plano.setNome(nome);
        plano.setDtCriacao(new Date());
        Plano salvo = planoRepository.save(plano);
        return toDTO(salvo);
    }

    // READ - todos
    public List<PlanoDTO> listarTodos() {
        return planoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // READ - por id
    public PlanoDTO buscarPorId(Long id) {
        return planoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    // UPDATE
    public PlanoDTO atualizar(Long id, PlanoDTO dto) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        plano.setNome(dto.getNome());
        plano.setDtAlteracao(new Date());
        Plano atualizado = planoRepository.save(plano);
        return toDTO(atualizado);
    }

    // DELETE
    public boolean deletar(Long id) {
        return planoRepository.findById(id).map(plano -> {
            planoRepository.delete(plano);
            return true;
        }).orElse(false);
    }

    // Conversão simples (pode ser substituída por Mapper)
    private PlanoDTO toDTO(Plano plano) {
        PlanoDTO dto = new PlanoDTO();
        dto.setNome(plano.getNome());
        return dto;
    }
}

