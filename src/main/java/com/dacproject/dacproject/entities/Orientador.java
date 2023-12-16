package com.dacproject.dacproject.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Orientador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;
    // Outros atributos

    @OneToMany(mappedBy = "orientador")
    private List<Aluno> alunos;

    public Orientador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
// Getters e setters
}