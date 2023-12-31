package com.dacproject.dacproject.dtos;

import com.dacproject.dacproject.entities.Aluno;
import com.dacproject.dacproject.entities.AvaliacaoOrientador;
import com.dacproject.dacproject.entities.Orientador;

import java.util.List;

public class OrientadorDTO {

    private Long id;
    private String matricula;
    private String nome;
    private List<Aluno> alunos;

    // Construtores, getters e setters

    public OrientadorDTO() {
        // Construtor padrão
    }

    public OrientadorDTO(Long id, String matricula, List<Aluno> alunos,String nome) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.alunos = alunos;
    }

    public OrientadorDTO(Orientador orientador) {
        this.id = orientador.getId();
        this.nome = orientador.getNome();
        this.matricula = orientador.getMatricula();
        this.alunos = orientador.getAlunos();
    }


    // Getters e setters

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

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrientadorDTO other = (OrientadorDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}