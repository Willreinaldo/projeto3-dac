package com.dacproject.dacproject.dtos;


import com.dacproject.dacproject.entities.Aluno;
import com.dacproject.dacproject.enums.AvaliacaoEnum;
import com.dacproject.dacproject.entities.Empresa;

public class AvaliacaoEmpresaDTO {

    private Long id;
    private Aluno aluno;
    private Empresa empresa;
    private AvaliacaoEnum rendimentoTrabalho;
    private AvaliacaoEnum conhecimentos;
    private AvaliacaoEnum cumprimentoTarefas;
    private AvaliacaoEnum aprendizagem;
    private AvaliacaoEnum desempenho;

    // Construtores, getters e setters

    public AvaliacaoEmpresaDTO() {
        // Construtor padrão
    }

    public AvaliacaoEmpresaDTO(Long id, Aluno aluno, Empresa empresa, AvaliacaoEnum rendimentoTrabalho,
                               AvaliacaoEnum conhecimentos, AvaliacaoEnum cumprimentoTarefas, AvaliacaoEnum aprendizagem,
                               AvaliacaoEnum desempenho) {
        this.id = id;
        this.aluno = aluno;
        this.empresa = empresa;
        this.rendimentoTrabalho = rendimentoTrabalho;
        this.conhecimentos = conhecimentos;
        this.cumprimentoTarefas = cumprimentoTarefas;
        this.aprendizagem = aprendizagem;
        this.desempenho = desempenho;
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
        AvaliacaoEmpresaDTO other = (AvaliacaoEmpresaDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}