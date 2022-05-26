package com.rsecinformation.cursomc.repositories;

import com.rsecinformation.cursomc.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
