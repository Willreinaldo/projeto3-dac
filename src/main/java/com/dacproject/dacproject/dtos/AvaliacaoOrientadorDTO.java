package com.dacproject.dacproject.dtos;

import com.dacproject.dacproject.entities.Aluno;
import com.dacproject.dacproject.entities.AvaliacaoEmpresa;
import com.dacproject.dacproject.entities.AvaliacaoOrientador;
import com.dacproject.dacproject.entities.Orientador;
import com.dacproject.dacproject.enums.AvaliacaoEnum;

public class AvaliacaoOrientadorDTO {

    private Long id;
    private Aluno aluno;
    private Orientador orientador;
    private AvaliacaoEnum assiduidade;
    private AvaliacaoEnum disciplina;
    private AvaliacaoEnum sociabilidade;
    private AvaliacaoEnum responsabilidade;
    private AvaliacaoEnum iniciativaSensoCritico;

    // Construtores, getters e setters

    public AvaliacaoOrientadorDTO() {
        // Construtor padrão
    }

    public AvaliacaoOrientadorDTO(Long id, Aluno aluno, Orientador orientador, AvaliacaoEnum assiduidade,
                                  AvaliacaoEnum disciplina, AvaliacaoEnum sociabilidade, AvaliacaoEnum responsabilidade,
                                  AvaliacaoEnum iniciativaSensoCritico) {
        this.id = id;
        this.aluno = aluno;
        this.orientador = orientador;
        this.assiduidade = assiduidade;
        this.disciplina = disciplina;
        this.sociabilidade = sociabilidade;
        this.responsabilidade = responsabilidade;
        this.iniciativaSensoCritico = iniciativaSensoCritico;
    }

    public AvaliacaoOrientadorDTO(AvaliacaoOrientador avaliacaoOrientador) {
        // Construtor padrão
        this.id = avaliacaoOrientador.getId();
        this.aluno = avaliacaoOrientador.getAluno();
        this.orientador = avaliacaoOrientador.getOrientador();
        this.assiduidade = avaliacaoOrientador.getAssiduidade();
        this.disciplina = avaliacaoOrientador.getDisciplina();
        this.sociabilidade = avaliacaoOrientador.getSociabilidade();
        this.responsabilidade = avaliacaoOrientador.getResponsabilidade();
        this.iniciativaSensoCritico = avaliacaoOrientador.getIniciativaSensoCritico();
    }
    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    public AvaliacaoEnum getAssiduidade() {
        return assiduidade;
    }

    public void setAssiduidade(AvaliacaoEnum assiduidade) {
        this.assiduidade = assiduidade;
    }

    public AvaliacaoEnum getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(AvaliacaoEnum disciplina) {
        this.disciplina = disciplina;
    }

    public AvaliacaoEnum getSociabilidade() {
        return sociabilidade;
    }

    public void setSociabilidade(AvaliacaoEnum sociabilidade) {
        this.sociabilidade = sociabilidade;
    }

    public AvaliacaoEnum getResponsabilidade() {
        return responsabilidade;
    }

    public void setResponsabilidade(AvaliacaoEnum responsabilidade) {
        this.responsabilidade = responsabilidade;
    }

    public AvaliacaoEnum getIniciativaSensoCritico() {
        return iniciativaSensoCritico;
    }

    public void setIniciativaSensoCritico(AvaliacaoEnum iniciativaSensoCritico) {
        this.iniciativaSensoCritico = iniciativaSensoCritico;
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
        AvaliacaoOrientadorDTO other = (AvaliacaoOrientadorDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

