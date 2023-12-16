package com.dacproject.dacproject.repositories;


import com.dacproject.dacproject.entities.Estagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstagioRepository extends JpaRepository<Estagio, Long> {
}
