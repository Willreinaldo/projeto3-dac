package com.dacproject.dacproject.services;

import com.dacproject.dacproject.entities.Orientador;
import com.dacproject.dacproject.dtos.OrientadorDTO;
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

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class OrientadorService{

    @Autowired
    private OrientadorRepository repository;
    @Transactional(readOnly = true)
    public Page<OrientadorDTO> findAllPaged(Pageable pageable) {
        try {
            Page<Orientador> list = repository.findAll(pageable);
            return list.map(OrientadorDTO::new);
        } catch (Exception e) {
            System.err.println("Erro ao buscar dados: " + e.getMessage());
            return Page.empty(pageable);
        }
    }

    @Transactional(readOnly = true)
    public Optional<OrientadorDTO> findById(Long id) {
        Optional<Orientador> obj = repository.findById(id);
        return obj.map(OrientadorDTO::new);
    }

    @Transactional
    public OrientadorDTO criarOrientador(OrientadorDTO dto) {
        Orientador entity = new Orientador();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new OrientadorDTO(entity);
    }



 @Transactional
    public OrientadorDTO atualizarOrientador(Long id, OrientadorDTO dto) {
        try {
            Orientador entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new OrientadorDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Orientador não encontrado com o ID: " + id);
        }
    }



    public void removerOrientador(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Orientador não encontrado com o ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade", e);
        }
    }

    private void copyDtoToEntity(OrientadorDTO dto, Orientador entity) {
         entity.setMatricula(dto.getMatricula());
        entity.setNome(dto.getNome());
        // Outros atributos do Orientador, se houver
    }
}