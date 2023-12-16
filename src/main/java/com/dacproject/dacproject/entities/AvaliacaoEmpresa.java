package com.dacproject.dacproject.entities;

import com.dacproject.dacproject.enums.AvaliacaoEnum;

import javax.persistence.*;

@Entity
@Table(name="AvaliacaoEmpresa")
public class AvaliacaoEmpresa {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Empresa empresa;

    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum rendimentoTrabalho;

    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum conhecimentos;

    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum cumprimentoTarefas;

    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum aprendizagem;

    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum desempenho;

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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public AvaliacaoEnum getRendimentoTrabalho() {
        return rendimentoTrabalho;
    }

    public void setRendimentoTrabalho(AvaliacaoEnum rendimentoTrabalho) {
        this.rendimentoTrabalho = rendimentoTrabalho;
    }

    public AvaliacaoEnum getConhecimentos() {
        return conhecimentos;
    }

    public void setConhecimentos(AvaliacaoEnum conhecimentos) {
        this.conhecimentos = conhecimentos;
    }

    public AvaliacaoEnum getCumprimentoTarefas() {
        return cumprimentoTarefas;
    }

    public void setCumprimentoTarefas(AvaliacaoEnum cumprimentoTarefas) {
        this.cumprimentoTarefas = cumprimentoTarefas;
    }

    public AvaliacaoEnum getAprendizagem() {
        return aprendizagem;
    }

    public void setAprendizagem(AvaliacaoEnum aprendizagem) {
        this.aprendizagem = aprendizagem;
    }

    public AvaliacaoEnum getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(AvaliacaoEnum desempenho) {
        this.desempenho = desempenho;
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
        AvaliacaoEmpresa other = (AvaliacaoEmpresa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
