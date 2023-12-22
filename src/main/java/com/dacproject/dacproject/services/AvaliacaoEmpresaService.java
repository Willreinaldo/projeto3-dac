package com.dacproject.dacproject.services;

import com.dacproject.dacproject.dtos.AvaliacaoEmpresaDTO;
import com.dacproject.dacproject.entities.AvaliacaoEmpresa;
import com.dacproject.dacproject.repositories.AvaliacaoEmpresaRepository;
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
public class AvaliacaoEmpresaService {

    @Autowired
    private AvaliacaoEmpresaRepository avaliacaoEmpresaRepository;

    @Transactional(readOnly = true)
    public Page<AvaliacaoEmpresaDTO> findAllPaged(Pageable pageable) {
        Page<AvaliacaoEmpresa> list = avaliacaoEmpresaRepository.findAll(pageable);
        return list.map(AvaliacaoEmpresaDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<AvaliacaoEmpresaDTO> findById(Long id) {
        try {
            Optional<AvaliacaoEmpresa> obj = avaliacaoEmpresaRepository.findById(id);
            return obj.map(AvaliacaoEmpresaDTO::new);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao encontrar Avaliação da Empresa com ID: " + id);
        }
    }

    @Transactional
    public AvaliacaoEmpresaDTO criarAvaliacaoEmpresa(AvaliacaoEmpresaDTO dto) {
        try {
            AvaliacaoEmpresa entity = new AvaliacaoEmpresa();
            copyDtoToEntity(dto, entity);
            entity = avaliacaoEmpresaRepository.save(entity);
            return new AvaliacaoEmpresaDTO(entity);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao criar uma nova Avaliação da Empresa", e);
        }
    }

    @Transactional
    public AvaliacaoEmpresaDTO atualizarAvaliacaoEmpresa(Long id, AvaliacaoEmpresaDTO dto) {
        try {
            AvaliacaoEmpresa entity = avaliacaoEmpresaRepository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = avaliacaoEmpresaRepository.save(entity);
            return new AvaliacaoEmpresaDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Avaliação da Empresa não encontrada com o ID: " + id);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao atualizar os dados da Avaliação da Empresa com ID: " + id, e);
        }
    }

    public void removerAvaliacaoEmpresa(Long id) {
        try {
            avaliacaoEmpresaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Avaliação da Empresa não encontrada com o ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade ao remover Avaliação da Empresa com ID: " + id, e);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao remover a Avaliação da Empresa com ID: " + id, e);
        }
    }

    private void copyDtoToEntity(AvaliacaoEmpresaDTO dto, AvaliacaoEmpresa entity) {
         entity.setAluno(dto.getAluno());
        entity.setEmpresa(dto.getEmpresa());
        entity.setRendimentoTrabalho(dto.getRendimentoTrabalho());
        entity.setConhecimentos(dto.getConhecimentos());
        entity.setCumprimentoTarefas(dto.getCumprimentoTarefas());
        entity.setAprendizagem(dto.getAprendizagem());
        entity.setDesempenho(dto.getDesempenho());
     }
}