package com.dacproject.dacproject.dtos;

import com.dacproject.dacproject.entities.Aluno;
import com.dacproject.dacproject.entities.Empresa;
import com.dacproject.dacproject.entities.Estagio;
import com.dacproject.dacproject.entities.Orientador;

public class AlunoDTO{

        private Long id;
        private String nome;
        private String matricula;
        private Orientador orientador;
        private Empresa empresa;
        private Estagio estagio;


        public AlunoDTO() {
            // Construtor padrão
        }

        public AlunoDTO(Long id, String nome, String matricula, Orientador orientador,
                        Empresa empresa, Estagio estagio) {
            this.id = id;
            this.nome = nome;
            this.matricula = matricula;
            this.orientador = orientador;
            this.empresa = empresa;
            this.estagio = estagio;
        }

    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.matricula = aluno.getMatricula();
        this.orientador = aluno.getOrientador();
        this.empresa = aluno.getEmpresa();
        this.estagio = aluno.getEstagio();
        // Outros atributos...
    }


    // Getters e setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public Orientador getOrientador() {
            return orientador;
        }

        public void setOrientador(Orientador orientador) {
            this.orientador = orientador;
        }

        public Empresa getEmpresa() {
            return empresa;
        }

        public void setEmpresa(Empresa empresa) {
            this.empresa = empresa;
        }

        public Estagio getEstagio() {
            return estagio;
        }

        public void setEstagio(Estagio estagio) {
            this.estagio = estagio;
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
            AlunoDTO other = (AlunoDTO) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }
    }