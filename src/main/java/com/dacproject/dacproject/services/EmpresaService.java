package com.dacproject.dacproject.services;


import com.dacproject.dacproject.dtos.EmpresaDTO;
import com.dacproject.dacproject.entities.Empresa;
import com.dacproject.dacproject.repositories.EmpresaRepository;
import com.dacproject.dacproject.services.exceptions.DatabaseException;
import com.dacproject.dacproject.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    @Transactional(readOnly = true)
    public Page<EmpresaDTO> findAllPaged(Pageable pageable) {
        Page<Empresa> list = repository.findAll(pageable);
        return list.map(EmpresaDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<EmpresaDTO> findById(Long id) {
        try {
            Optional<Empresa> obj = repository.findById(id);
            return obj.map(EmpresaDTO::new);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao encontrar Empresa com ID: " + id);
        }
    }

    @Transactional
    public EmpresaDTO criarEmpresa(EmpresaDTO dto) {
        try {
            Empresa entity = new Empresa();
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EmpresaDTO(entity);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao criar uma nova Empresa");
        }
    }

    @Transactional
    public EmpresaDTO atualizarEmpresa(Long id, EmpresaDTO dto) {
        try {
            Empresa entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EmpresaDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Empresa não encontrada com o ID: " + id);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao atualizar os dados da Empresa com ID: " + id);
        }
    }

    public void removerEmpresa(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Empresa não encontrada com o ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade ao remover Empresa com ID: " + id);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao remover a Empresa com ID: " + id);
        }
    }

    private void copyDtoToEntity(EmpresaDTO dto, Empresa entity) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
        // Se necessário, adicione aqui a lógica para manipular a lista de alunos associados à empresa
    }
}