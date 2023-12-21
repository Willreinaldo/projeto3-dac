package com.dacproject.dacproject.services;

import com.dacproject.dacproject.dtos.AvaliacaoOrientadorDTO;
import com.dacproject.dacproject.entities.AvaliacaoOrientador;
import com.dacproject.dacproject.repositories.AvaliacaoOrientadorRepository;
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
    public AvaliacaoOrientador criarAvaliacaoOrientador(AvaliacaoOrientador avaliacaoOrientador) {
        return avaliacaoOrientadorRepository.save(avaliacaoOrientador);
    }

    @Transactional
    public AvaliacaoOrientador atualizarAvaliacaoOrientador(Long id, AvaliacaoOrientador novaAvaliacaoOrientador) {
        AvaliacaoOrientador avaliacao = avaliacaoOrientadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação do Orientador não encontrada com o ID fornecido."));

        copyAvaliacaoOrientadorDtoToEntity(novaAvaliacaoOrientador, avaliacao);

        return avaliacaoOrientadorRepository.save(avaliacao);
    }

    @Transactional
    public void removerAvaliacaoOrientador(Long id) {
        avaliacaoOrientadorRepository.deleteById(id);
    }

    private void copyAvaliacaoOrientadorDtoToEntity(AvaliacaoOrientador dto, AvaliacaoOrientador entity) {
        entity.setAssiduidade(dto.getAssiduidade());
        entity.setDisciplina(dto.getDisciplina());
        entity.setSociabilidade(dto.getSociabilidade());
        entity.setResponsabilidade(dto.getResponsabilidade());
        entity.setIniciativaSensoCritico(dto.getIniciativaSensoCritico());
        // Definir outros campos conforme necessário
    }
}