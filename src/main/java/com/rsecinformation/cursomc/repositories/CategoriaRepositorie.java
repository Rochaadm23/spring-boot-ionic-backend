package com.rsecinformation.cursomc.repositories;

import com.rsecinformation.cursomc.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorie extends JpaRepository<Categoria, Integer> {}
