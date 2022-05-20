package com.rsecinformation.cursomc.repositories;

import com.rsecinformation.cursomc.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepositorie extends JpaRepository<Estado, Integer> {
}
