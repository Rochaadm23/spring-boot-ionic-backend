package com.rsecinformation.cursomc.repositories;

import com.rsecinformation.cursomc.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
