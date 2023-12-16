package com.dacproject.dacproject.dtos;

import com.dacproject.dacproject.entities.Aluno;
import com.dacproject.dacproject.entities.Empresa;
import com.dacproject.dacproject.entities.Orientador;

import java.util.Date;

public class EstagioDTO {

    private Long id;
    private Date inicioEstagio;
    private Date fimEstagio;
    private int cargaHoraria;
    private String status;
    private Aluno aluno;
    private Empresa empresa;
    private Orientador orientador;

    // Construtores, getters e setters

    public EstagioDTO() {
        // Construtor padrão
    }

    public EstagioDTO(Long id, Date inicioEstagio, Date fimEstagio, int cargaHoraria, String status, Aluno aluno,
                      Empresa empresa, Orientador orientador) {
        this.id = id;
        this.inicioEstagio = inicioEstagio;
        this.fimEstagio = fimEstagio;
        this.cargaHoraria = cargaHoraria;
        this.status = status;
        this.aluno = aluno;
        this.empresa = empresa;
        this.orientador = orientador;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInicioEstagio() {
        return inicioEstagio;
    }

    public void setInicioEstagio(Date inicioEstagio) {
        this.inicioEstagio = inicioEstagio;
    }

    public Date getFimEstagio() {
        return fimEstagio;
    }

    public void setFimEstagio(Date fimEstagio) {
        this.fimEstagio = fimEstagio;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
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
        EstagioDTO other = (EstagioDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
