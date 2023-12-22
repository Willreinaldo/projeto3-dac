package com.dacproject.dacproject.repositories;

import com.dacproject.dacproject.entities.Orientador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrientadorRepository extends JpaRepository<Orientador, Long> {

}
