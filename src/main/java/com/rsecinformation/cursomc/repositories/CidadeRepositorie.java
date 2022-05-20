package com.rsecinformation.cursomc.repositories;

import com.rsecinformation.cursomc.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepositorie extends JpaRepository<Cidade, Integer> {
}
