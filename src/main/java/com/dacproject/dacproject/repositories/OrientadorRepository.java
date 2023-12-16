package com.dacproject.dacproject.repositories;

import com.dacproject.dacproject.entities.Orientador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrientadorRepository extends JpaRepository<Orientador, Long> {

}
