package com.dacproject.dacproject.services;

import com.dacproject.dacproject.dtos.AvaliacaoOrientadorDTO;
import com.dacproject.dacproject.entities.AvaliacaoOrientador;
import com.dacproject.dacproject.repositories.AvaliacaoOrientadorRepository;
import com.dacproject.dacproject.services.exceptions.DatabaseException;
import com.dacproject.dacproject.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoOrientadorService {

    @Autowired
    private AvaliacaoOrientadorRepository avaliacaoOrientadorRepository;


    @Transactional(readOnly = true)
    public List<AvaliacaoOrientador> listarAvaliacoesOrientador() {
        return avaliacaoOrientadorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<AvaliacaoOrientador> buscarAvaliacaoOrientadorPorId(Long id) {
        return avaliacaoOrientadorRepository.findById(id);
    }


    @Transactional
    public AvaliacaoOrientadorDTO criarAvaliacaoOrientador(AvaliacaoOrientadorDTO dto) {
        try {
            AvaliacaoOrientador entity = new AvaliacaoOrientador();
            copyAvaliacaoOrientadorDtoToEntity(dto, entity);
            entity = avaliacaoOrientadorRepository.save(entity);
            return new AvaliacaoOrientadorDTO(entity);
         } catch (Exception e) {
            // Tratar exceção ou lançar uma exceção personalizada, se necessário
            throw new DatabaseException("Erro ao criar a Avaliação do Orientador", e);
        }
    }
    @Transactional
    public AvaliacaoOrientadorDTO atualizarAvaliacaoOrientador(Long id, AvaliacaoOrientadorDTO dto) {
        try {
            AvaliacaoOrientador entity = avaliacaoOrientadorRepository.getOne(id);
            copyAvaliacaoOrientadorDtoToEntity(dto, entity);
            entity = avaliacaoOrientadorRepository.save(entity);
            return new AvaliacaoOrientadorDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Avaliação da Empresa não encontrada com o ID: " + id);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao atualizar os dados da Avaliação da Empresa com ID: " + id, e);
        }
    }

    @Transactional
    public void removerAvaliacaoOrientador(Long id) {
        avaliacaoOrientadorRepository.deleteById(id);
    }

    private void copyAvaliacaoOrientadorDtoToEntity(AvaliacaoOrientadorDTO dto, AvaliacaoOrientador entity) {
        entity.setAluno(dto.getAluno());
        entity.setOrientador(dto.getOrientador());
        entity.setAssiduidade(dto.getAssiduidade());
        entity.setDisciplina(dto.getDisciplina());
        entity.setSociabilidade(dto.getSociabilidade());
        entity.setResponsabilidade(dto.getResponsabilidade());
        entity.setIniciativaSensoCritico(dto.getIniciativaSensoCritico());
        // Definir outros campos conforme necessário
    }
}