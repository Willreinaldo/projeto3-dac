package com.dacproject.dacproject.resources;

import com.dacproject.dacproject.dtos.AlunoDTO;
import com.dacproject.dacproject.services.AlunoService;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

    private final AlunoService alunoService;

    @Autowired
    public AlunoResource(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<Page<AlunoDTO>> findAll(Pageable pageable) {
        Page<AlunoDTO> list = alunoService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> obterAlunoPorId(@PathVariable Long id) {
        Optional<AlunoDTO> aluno = alunoService.findById(id);
        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> criarAluno(@RequestBody @Valid AlunoDTO novoAluno) {
        try {
            AlunoDTO alunoCriado = alunoService.criarAluno(novoAluno);
            return ResponseEntity.status(HttpStatus.CREATED).body(alunoCriado);
        } catch (Exception e) {
            // Aqui você pode tratar a exceção de validação ou de serviço
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable @Valid Long id, @RequestBody AlunoDTO alunoAtualizado) {
        try {
            alunoAtualizado.setId(id);
            AlunoDTO aluno = alunoService.atualizarAluno(id, alunoAtualizado);
            return ResponseEntity.ok(aluno);
        } catch (Exception e) {
            // Tratar exceção de não encontrar o Aluno ou outra exceção de serviço
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAluno(@PathVariable Long id) {
        try {
            alunoService.removerAluno(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Tratar exceção de não encontrar o Aluno ou outra exceção de serviço
            return ResponseEntity.notFound().build();
        }
    }
}

