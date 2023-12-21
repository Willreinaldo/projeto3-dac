package com.dacproject.dacproject.resources;

import com.dacproject.dacproject.dtos.AvaliacaoEmpresaDTO;
import com.dacproject.dacproject.services.AvaliacaoEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoesempresa")
public class AvaliacaoEmpresaResource {

    private final AvaliacaoEmpresaService avaliacaoEmpresaService;

    @Autowired
    public AvaliacaoEmpresaResource(AvaliacaoEmpresaService avaliacaoEmpresaService) {
        this.avaliacaoEmpresaService = avaliacaoEmpresaService;
    }

    @GetMapping
    public ResponseEntity<Page<AvaliacaoEmpresaDTO>> listarAvaliacoesEmpresa(Pageable pageable) {
        Page<AvaliacaoEmpresaDTO> avaliacoes = avaliacaoEmpresaService.findAllPaged(pageable);
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoEmpresaDTO> buscarAvaliacaoEmpresaPorId(@PathVariable Long id) {
        Optional<AvaliacaoEmpresaDTO> avaliacao = avaliacaoEmpresaService.findById(id);
        return avaliacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AvaliacaoEmpresaDTO> criarAvaliacaoEmpresa(@RequestBody @Valid AvaliacaoEmpresaDTO novaAvaliacaoEmpresa) {
        try {
            AvaliacaoEmpresaDTO avaliacaoCriada = avaliacaoEmpresaService.criarAvaliacaoEmpresa(novaAvaliacaoEmpresa);
            return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoCriada);
        } catch (Exception e) {
            // Tratar exceção de validação ou de serviço
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoEmpresaDTO> atualizarAvaliacaoEmpresa(@PathVariable @Valid Long id, @RequestBody AvaliacaoEmpresaDTO avaliacaoAtualizada) {
        try {
            avaliacaoAtualizada.setId(id);
            AvaliacaoEmpresaDTO avaliacao = avaliacaoEmpresaService.atualizarAvaliacaoEmpresa(id, avaliacaoAtualizada);
            return ResponseEntity.ok(avaliacao);
        } catch (Exception e) {
            // Tratar exceção de não encontrar a Avaliação da Empresa ou outra exceção de serviço
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAvaliacaoEmpresa(@PathVariable Long id) {
        try {
            avaliacaoEmpresaService.removerAvaliacaoEmpresa(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Tratar exceção de não encontrar a Avaliação da Empresa ou outra exceção de serviço
            return ResponseEntity.notFound().build();
        }
    }
}