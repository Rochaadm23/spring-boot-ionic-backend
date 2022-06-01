package com.rsecinformation.cursomc.repositories;

import com.rsecinformation.cursomc.entities.Categoria;
import com.rsecinformation.cursomc.entities.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    //@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.name LIKE %:name% AND cat IN :categorias")
    Page<Produto> findByNameContainingAndCategoriasIn(@Param("name") String name, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}
