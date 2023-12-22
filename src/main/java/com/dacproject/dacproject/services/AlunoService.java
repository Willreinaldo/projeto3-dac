package com.dacproject.dacproject.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.dacproject.dacproject.entities.Empresa;
import com.dacproject.dacproject.entities.Estagio;
import com.dacproject.dacproject.entities.Orientador;
import com.dacproject.dacproject.repositories.EmpresaRepository;
import com.dacproject.dacproject.repositories.EstagioRepository;
import com.dacproject.dacproject.repositories.OrientadorRepository;
import com.dacproject.dacproject.services.exceptions.DatabaseException;
import com.dacproject.dacproject.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dacproject.dacproject.dtos.AlunoDTO;
import com.dacproject.dacproject.entities.Aluno;
import com.dacproject.dacproject.repositories.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private OrientadorRepository orientadorRepository;

    @Autowired
    private EstagioRepository estagioRepository;



    @Transactional(readOnly = true)
    public Page<AlunoDTO> findAllPaged(Pageable pageable) {
        Page<Aluno> list = repository.findAll(pageable);
        return list.map(AlunoDTO::new);
    }


    @Transactional(readOnly = true)
    public Optional<AlunoDTO> findById(Long id) {
        Optional<Aluno> obj = repository.findById(id);
        return obj.map(AlunoDTO::new);
    }

    @Transactional
    public AlunoDTO criarAluno(AlunoDTO alunoDTO) {
        try {
            Aluno aluno = new Aluno();
            copyDtoToEntity(alunoDTO, aluno);

            Optional<Empresa> empresaOptional = empresaRepository.findById(alunoDTO.getEmpresa().getId());
            Empresa empresa = empresaOptional.orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com o ID fornecido."));
            aluno.setEmpresa(empresa);

            Optional<Orientador> orientadorOptional = orientadorRepository.findById(alunoDTO.getOrientador().getId());
            Orientador orientador = orientadorOptional.orElseThrow(() -> new ResourceNotFoundException("Orientador não encontrado com o ID fornecido."));
            aluno.setOrientador(orientador);

            Optional<Estagio> estagioOptional = estagioRepository.findById(alunoDTO.getEstagio().getId());
            Estagio estagio = estagioOptional.orElseThrow(() -> new ResourceNotFoundException("Estagio não encontrado com o ID fornecido."));
            aluno.setEstagio(estagio);

            aluno = repository.save(aluno);
             repository.save(aluno);

            return new AlunoDTO(aluno);
        } catch (ResourceNotFoundException e) {
            System.out.println("Erro ao criar aluno ou estágio: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Erro ao criar aluno ou estágio: " + e.getMessage());
            throw new DatabaseException("Erro ao criar um novo Aluno ou Estágio", e);
        }
    }
    @Transactional
    public AlunoDTO atualizarAluno(Long id, AlunoDTO dto) {
        try {
            Aluno entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new AlunoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Aluno not found " + id);
        }
    }

    public void removerAluno(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Aluno not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation", e);
        }
    }

    private void copyDtoToEntity(AlunoDTO dto, Aluno entity) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setMatricula(dto.getMatricula());
        entity.setOrientador(dto.getOrientador());
        entity.setEmpresa(dto.getEmpresa());
        entity.setEstagio(dto.getEstagio());
    }
}