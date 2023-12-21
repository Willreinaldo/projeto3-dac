package com.dacproject.dacproject.resources;

import com.dacproject.dacproject.dtos.EstagioDTO;
import com.dacproject.dacproject.services.EstagioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/estagios")
public class EstagioResource {

    private final EstagioService estagioService;

    @Autowired
    public EstagioResource(EstagioService estagioService) {
        this.estagioService = estagioService;
    }

    @GetMapping
    public ResponseEntity<Page<EstagioDTO>> findAll(Pageable pageable) {
        Page<EstagioDTO> list = estagioService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstagioDTO> obterEstagioPorId(@PathVariable Long id) {
        Optional<EstagioDTO> estagio = estagioService.findById(id);
        return estagio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstagioDTO> criarEstagio(@RequestBody @Valid EstagioDTO novoEstagio) {
        try {
            EstagioDTO estagioCriado = estagioService.criarEstagioComDto(novoEstagio);
            return ResponseEntity.status(HttpStatus.CREATED).body(estagioCriado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstagioDTO> atualizarEstagio(@PathVariable @Valid Long id, @RequestBody EstagioDTO estagioAtualizado) {
        try {
            estagioAtualizado.setId(id);
            EstagioDTO estagio = estagioService.atualizarEstagioComDto(id, estagioAtualizado);
            return ResponseEntity.ok(estagio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEstagio(@PathVariable Long id) {
        try {
            estagioService.removerEstagio(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}