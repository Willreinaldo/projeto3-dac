package com.dacproject.dacproject.services;

import com.dacproject.dacproject.dtos.EstagioDTO;
import com.dacproject.dacproject.entities.Estagio;
import com.dacproject.dacproject.repositories.AlunoRepository;
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
import java.util.Optional;
@Service
public class EstagioService {

    @Autowired
    private EstagioRepository estagioRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private OrientadorRepository orientadorRepository;

    @Transactional(readOnly = true)
    public Page<EstagioDTO> findAllPaged(Pageable pageable) {
        Page<Estagio> list = estagioRepository.findAll(pageable);
        return list.map(EstagioDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<EstagioDTO> findById(Long id) {
        Optional<Estagio> estagio = estagioRepository.findById(id);
        return estagio.map(EstagioDTO::new);
    }

    @Transactional
    public EstagioDTO criarEstagioComDto(EstagioDTO estagioDTO) {
        try {
            Estagio estagio = new Estagio();
            copyEstagioDtoToEntity(estagioDTO, estagio);

            estagio = estagioRepository.save(estagio);
            return new EstagioDTO(estagio);
        } catch (Exception e) {
            System.out.println("Erro ao criar estágio: " + e.getMessage());
            throw new DatabaseException("Erro ao criar um novo Estágio");
        }
    }

    @Transactional
    public EstagioDTO atualizarEstagioComDto(Long id, EstagioDTO novoEstagioDTO) {
        try {
            Estagio estagio = estagioRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Estágio não encontrado com o ID fornecido."));

            copyEstagioDtoToEntity(novoEstagioDTO, estagio);

            estagio = estagioRepository.save(estagio);
            return new EstagioDTO(estagio);
        } catch (ResourceNotFoundException e) {
            System.out.println("Erro ao atualizar estágio: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar estágio: " + e.getMessage());
            throw new DatabaseException("Erro ao atualizar o Estágio");
        }
    }

    public void removerEstagio(Long id) {
        try {
            estagioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Estágio não encontrado com o ID fornecido.");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }
    }

    private void copyEstagioDtoToEntity(EstagioDTO dto, Estagio entity) {
        entity.setInicioEstagio(dto.getInicioEstagio());
        entity.setFimEstagio(dto.getFimEstagio());
        entity.setCargaHoraria(dto.getCargaHoraria());
        entity.setStatus(dto.getStatus());
        entity.setOrientador(dto.getOrientador());
        entity.setEmpresa(dto.getEmpresa());
        entity.setAluno(dto.getAluno());
     }
}
