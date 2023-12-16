package com.dacproject.dacproject.resources;

import com.dacproject.dacproject.entities.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

    private final AlunoService alunoService;

    @Autowired
    public AlunoResource(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.criarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> obterAlunoPorId(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.encontrarAlunoPorId(id);
        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoService.listarTodosAlunos();
        return ResponseEntity.ok(alunos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        alunoAtualizado.setId(id);
        Aluno aluno = alunoService.atualizarAluno(alunoAtualizado);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAluno(@PathVariable Long id) {
        alunoService.removerAluno(id);
        return ResponseEntity.noContent().build();
    }
}

