package com.dacproject.dacproject.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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
    public AlunoDTO criarAluno(AlunoDTO dto) {
        Aluno entity = new Aluno();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new AlunoDTO(entity);
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
            throw new DatabaseException("Integrity violation");
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