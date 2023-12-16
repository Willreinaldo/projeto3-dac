package com.dacproject.dacproject.entities;

import javax.persistence.*;

@Entity
public class AvaliacaoEmpresa {
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
}
