package com.dacproject.dacproject.repositories;

import com.dacproject.dacproject.entities.Aluno;
import com.dacproject.dacproject.entities.AvaliacaoOrientador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoOrientadorRepository extends JpaRepository<AvaliacaoOrientador,Long> {
}
