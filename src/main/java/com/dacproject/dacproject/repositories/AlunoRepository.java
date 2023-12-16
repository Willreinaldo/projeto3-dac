package com.dacproject.dacproject.repositories;

import com.dacproject.dacproject.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long> {
}
