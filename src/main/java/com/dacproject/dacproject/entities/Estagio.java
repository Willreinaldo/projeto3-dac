package com.dacproject.dacproject.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Estagio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date inicioEstagio;
    private Date fimEstagio;
    private int cargaHoraria;
    private String status; // "em andamento" ou "finalizado"

    @OneToOne
    private Aluno aluno;

    @OneToOne
    private Empresa empresa;

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

    @OneToOne
    private Orientador orientador;

    // Getters e setters
}