package com.dacproject.dacproject.resources;
import com.dacproject.dacproject.dtos.EmpresaDTO;
import com.dacproject.dacproject.services.EmpresaService;
import com.dacproject.dacproject.services.exceptions.DatabaseException;
import com.dacproject.dacproject.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaResource(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<Page<EmpresaDTO>> buscarEmpresas(Pageable pageable) {
        try {
            Page<EmpresaDTO> empresas = empresaService.findAllPaged(pageable);
            return ResponseEntity.ok(empresas);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar as empresas");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> buscarEmpresaPorId(@PathVariable Long id) {
        try {
            return empresaService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Empresa não encontrada com o ID: " + id);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar a empresa com ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> criarEmpresa(@RequestBody EmpresaDTO novaEmpresa) {
        try {
            EmpresaDTO empresaCriada = empresaService.criarEmpresa(novaEmpresa);
            return ResponseEntity.status(HttpStatus.CREATED).body(empresaCriada);
        } catch (DatabaseException e) {
            throw new DatabaseException("Erro ao criar uma nova Empresa");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> atualizarEmpresa(@PathVariable Long id,@Valid @RequestBody EmpresaDTO empresaDTO) {
        try {
            EmpresaDTO empresaAtualizada = empresaService.atualizarEmpresa(id, empresaDTO);
            return ResponseEntity.ok(empresaAtualizada);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Empresa não encontrada com o ID: " + id);
        } catch (DatabaseException e) {
            throw new DatabaseException("Erro ao atualizar os dados da Empresa com ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEmpresa(@PathVariable Long id) {
        try {
            empresaService.removerEmpresa(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Empresa não encontrada com o ID: " + id);
        } catch (DatabaseException e) {
            throw new DatabaseException("Erro ao remover a Empresa com ID: " + id);
        }
    }
}