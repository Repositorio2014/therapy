package br.com.therapy.controller;

import br.com.therapy.dto.PlanoDTO;
import br.com.therapy.service.PlanoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @PostMapping
    public ResponseEntity<PlanoDTO> criar(@RequestParam String nome) {
        PlanoDTO novoPlano = planoService.criar(nome);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPlano);
    }


    @GetMapping
    public ResponseEntity<List<PlanoDTO>> listarTodos() {
        return ResponseEntity.ok(planoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(planoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoDTO> atualizar(@PathVariable Long id, @RequestParam String nome) {
        return ResponseEntity.ok(planoService.atualizar(id, nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        boolean deletado = planoService.deletar(id);
        if (deletado) {
            return ResponseEntity.ok("Plano deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano não encontrado");
        }
    }
}

