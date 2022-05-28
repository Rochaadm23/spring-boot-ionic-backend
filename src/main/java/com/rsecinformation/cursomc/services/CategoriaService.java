package com.rsecinformation.cursomc.services;

import com.rsecinformation.cursomc.dto.CategoriaDTO;
import com.rsecinformation.cursomc.entities.Categoria;
import com.rsecinformation.cursomc.repositories.CategoriaRepository;
import com.rsecinformation.cursomc.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Categoria insert(Categoria obj) {
        return categoriaRepository.save(obj);
    }

    public Categoria fromDTO(CategoriaDTO objDto){
        return new Categoria(objDto.getId(), objDto.getName());
    }
}
