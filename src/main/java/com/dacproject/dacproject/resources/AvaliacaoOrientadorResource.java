package com.dacproject.dacproject.resources;

import com.dacproject.dacproject.entities.AvaliacaoOrientador;
import com.dacproject.dacproject.services.AvaliacaoOrientadorService;
import com.dacproject.dacproject.services.exceptions.DatabaseException;
import com.dacproject.dacproject.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/avaliacoesorientador")
public class AvaliacaoOrientadorResource {

    private final AvaliacaoOrientadorService avaliacaoOrientadorService;

    @Autowired
    public AvaliacaoOrientadorResource(AvaliacaoOrientadorService avaliacaoOrientadorService) {
        this.avaliacaoOrientadorService = avaliacaoOrientadorService;
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoOrientador>> listarAvaliacoesOrientador() {
        try {
            List<AvaliacaoOrientador> avaliacoes = avaliacaoOrientadorService.listarAvaliacoesOrientador();
            return ResponseEntity.ok(avaliacoes);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar as avaliações do orientador");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoOrientador> buscarAvaliacaoOrientadorPorId(@PathVariable Long id) {
        try {
            return avaliacaoOrientadorService.buscarAvaliacaoOrientadorPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Avaliação do orientador não encontrada com o ID: " + id);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar a avaliação do orientador com ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<AvaliacaoOrientador> criarAvaliacaoOrientador(@RequestBody AvaliacaoOrientador novaAvaliacaoOrientador) {
        try {
            AvaliacaoOrientador avaliacaoCriada = avaliacaoOrientadorService.criarAvaliacaoOrientador(novaAvaliacaoOrientador);
            return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoCriada);
        } catch (DatabaseException e) {
            throw new DatabaseException("Erro ao criar uma nova avaliação do orientador");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoOrientador> atualizarAvaliacaoOrientador(@PathVariable Long id, @Valid @RequestBody AvaliacaoOrientador novaAvaliacaoOrientador) {
        try {
            AvaliacaoOrientador avaliacaoAtualizada = avaliacaoOrientadorService.atualizarAvaliacaoOrientador(id, novaAvaliacaoOrientador);
            return ResponseEntity.ok(avaliacaoAtualizada);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Avaliação do orientador não encontrada com o ID: " + id);
        } catch (DatabaseException e) {
            throw new DatabaseException("Erro ao atualizar a avaliação do orientador com ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAvaliacaoOrientador(@PathVariable Long id) {
        try {
            avaliacaoOrientadorService.removerAvaliacaoOrientador(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Avaliação do orientador não encontrada com o ID: " + id);
        } catch (DatabaseException e) {
            throw new DatabaseException("Erro ao remover a avaliação do orientador com ID: " + id);
        }
    }
}