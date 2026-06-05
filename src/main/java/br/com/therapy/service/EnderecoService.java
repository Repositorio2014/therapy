package br.com.therapy.service;

import br.com.therapy.client.ViaCepClient;
import br.com.therapy.dto.EnderecoResponse;
import br.com.therapy.model.Endereco;
import br.com.therapy.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EnderecoService {

    private final ViaCepClient viaCepClient;
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(ViaCepClient viaCepClient, EnderecoRepository enderecoRepository) {
        this.viaCepClient = viaCepClient;
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco buscarPorCep(String cep) {
        EnderecoResponse response = viaCepClient.getEndereco(cep);

        return  Endereco.builder()
                .cep(response.getCep())
                .logradouro(response.getLogradouro())
                .complemento(response.getComplemento())
                .bairro(response.getBairro())
                .localidade(response.getLocalidade())
                .uf(response.getUf())
                .dtCriacao(new Date())
                .build();
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Long id, Endereco enderecoAtualizado) {
        return enderecoRepository.findById(id)
                .map(endereco -> {
                    endereco.setCep(enderecoAtualizado.getCep());
                    endereco.setLogradouro(enderecoAtualizado.getLogradouro());
                    endereco.setComplemento(enderecoAtualizado.getComplemento());
                    endereco.setBairro(enderecoAtualizado.getBairro());
                    endereco.setLocalidade(enderecoAtualizado.getLocalidade());
                    endereco.setUf(enderecoAtualizado.getUf());
                    endereco.setDtAlteracao(enderecoAtualizado.getDtAlteracao());
                    return enderecoRepository.save(endereco);
                })
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public void deletar(Long id) {
        enderecoRepository.deleteById(id);
    }
}
