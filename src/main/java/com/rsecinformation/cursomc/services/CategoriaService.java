package com.rsecinformation.cursomc.services;

import com.rsecinformation.cursomc.entities.Categoria;
import com.rsecinformation.cursomc.repositories.CategoriaRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepositorie categoriaRepositorie;

    public Optional<Categoria> findById(Integer id){
        Optional<Categoria> obj = categoriaRepositorie.findById(id);
        return obj;
    }
}
