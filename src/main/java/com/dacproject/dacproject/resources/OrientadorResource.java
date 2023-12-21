package com.dacproject.dacproject.resources;

import com.dacproject.dacproject.dtos.OrientadorDTO;
import com.dacproject.dacproject.services.OrientadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orientador")
public class OrientadorResource {

    @Autowired
    private OrientadorService orientadorService;

    @GetMapping
    public ResponseEntity<Page<OrientadorDTO>> findAll(Pageable pageable) {
        try {
            Page<OrientadorDTO> list = orientadorService.findAllPaged(pageable);
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrientadorDTO> findById(@PathVariable Long id) {
        return orientadorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrientadorDTO> criarOrientador(@RequestBody @Valid OrientadorDTO  novoOrientador) {
        OrientadorDTO orientadorCriado = orientadorService.criarOrientador(novoOrientador);
        return ResponseEntity.status(HttpStatus.CREATED).body(orientadorCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrientadorDTO> atualizarOrientador(@PathVariable @Valid Long id, @RequestBody OrientadorDTO orientadorAtualizado) {
        OrientadorDTO orientador = orientadorService.atualizarOrientador(id, orientadorAtualizado);
        return ResponseEntity.ok(orientador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerOrientador(@PathVariable Long id) {
        orientadorService.removerOrientador(id);
        return ResponseEntity.noContent().build();
    }
}