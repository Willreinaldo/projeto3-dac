package com.dacproject.dacproject.entities;

import com.dacproject.dacproject.enums.AvaliacaoEnum;

import javax.persistence.*;

@Entity
@Table(name="AvaliacaoOrientador")
public class AvaliacaoOrientador {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Orientador orientador;

    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum assiduidade;

    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum disciplina;

    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum sociabilidade;

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

    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum responsabilidade;

    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum iniciativaSensoCritico;

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
        AvaliacaoOrientador other = (AvaliacaoOrientador) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
